package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.presentation.view.base.BaseActivityMVVM
import com.example.data.AppStateMVVM
import com.example.data.DataModelMVVM
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.dictionaryMVP.view.main.SearchDialogFragment
import com.example.dictionary.mvvm.presentation.view.history.HistoryFragment
import com.example.dictionary.mvvm.presentation.view.history.SeacrhInHistoryDialogFragment
import com.example.dictionary.mvvm.presentation.viewModel.MainViewModel
import com.example.dictionary.mvvm.presentation.viewModel.adapter.MainAdapterMVVM
import com.example.dictionary.mvvm.utils.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class MainActivityMVVM : BaseActivityMVVM<AppStateMVVM>() {

    private val mainActivityRecyclerView by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)

    private val scopeActivity by lazy {
        getKoin().createScope("mainActivityId", named<MainActivityMVVM>())
    }

    override lateinit var model: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val adapter: MainAdapterMVVM? = null

    private val fabClickListener: View.OnClickListener = View.OnClickListener {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
        searchDialogFragment.show(
            supportFragmentManager,
            BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
        )
    }

    private val onListItemClickListener: MainAdapterMVVM.OnListItemClickListener =
        object : MainAdapterMVVM.OnListItemClickListener {
            override fun onItemClick(data: DataModelMVVM) {
                if (!data.text.isNullOrBlank() && !data.meanings.isNullOrEmpty()) {
                    val currentMeanings = data.meanings
                    if (currentMeanings != null) {
                        for (meaning in currentMeanings) {
                            val currentTranslations = meaning.translation
                            if (currentTranslations != null &&
                                !currentTranslations.translation.isNullOrBlank()
                            ) {
                                val currentText = data.text
                                val dialog = currentText?.let {
                                    TranslationDialogFragment(
                                        it,
                                        currentTranslations.translation!!,
                                        meaning.imageUrl.toString()
                                    )
                                }
                                dialog?.show(
                                    supportFragmentManager,
                                    "translation with image dialog"
                                )
                            }
                        }
                    }
                }
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                model.getData(searchWord, true)
            }
        }

    private val onSearchInHistoryClickListener: SeacrhInHistoryDialogFragment.OnSearchInHistoryClickListener =
        object : SeacrhInHistoryDialogFragment.OnSearchInHistoryClickListener {
            override fun onClick(searchWord: String) {

                startHistoryFragment(searchWord)
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searched_words -> {
                startHistoryFragment("")
                return true
            }
            else -> {
                val searchInHistoryDialogFragment = SeacrhInHistoryDialogFragment.newInstance()
                searchInHistoryDialogFragment.setOnSearchInHistoryClickListener(
                    onSearchInHistoryClickListener
                )
                searchInHistoryDialogFragment.show(
                    supportFragmentManager,
                    BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
                )
                return true
            }
        }
    }

    private fun startHistoryFragment(word: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HistoryFragment(word))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    override fun renderData(appStateMVVM: AppStateMVVM) {
        when (appStateMVVM) {
            is AppStateMVVM.Success -> {
                val dataModel = appStateMVVM.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapterMVVM(onListItemClickListener, dataModel)
                    } else {
                        adapter.setData(dataModel)
                    }
                }
            }
            is AppStateMVVM.Loading -> {
                val currentProgress = appStateMVVM.progress
                showViewLoading()
                if (appStateMVVM.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    currentProgress?.let {
                        binding.progressBarHorizontal.progress = it
                    }
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppStateMVVM.Error -> {
                showErrorScreen(appStateMVVM.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    private fun initViews() {
        searchFab.setOnClickListener(fabClickListener)
        mainActivityRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        if (mainActivityRecyclerView.adapter != null) {
            throw IllegalStateException("ViewModel not initialised")
        }
        val viewModel: MainViewModel by scopeActivity.inject()
        model = viewModel
        model.subscribe().observe(this@MainActivityMVVM) {
            renderData(it)
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun onDestroy() {
        scopeActivity.close()
        super.onDestroy()
    }
}