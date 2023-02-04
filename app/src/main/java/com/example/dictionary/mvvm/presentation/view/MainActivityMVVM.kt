package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.dictionaryMVP.view.main.SearchDialogFragment
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.presentation.view.base.BaseActivityMVVM
import com.example.dictionary.mvvm.presentation.viewModel.MainViewModel
import com.example.dictionary.mvvm.presentation.viewModel.adapter.MainAdapterMVVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivityMVVM : BaseActivityMVVM<AppStateMVVM>() {

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
                Toast.makeText(
                    this@MainActivityMVVM, data.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                model.getData(searchWord, true)
            }
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
                showViewLoading()
                if (appStateMVVM.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appStateMVVM.progress
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
        binding.apply {
            searchFab.setOnClickListener(fabClickListener)
            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter
        }
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel not initialised")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivityMVVM) {
            renderData(it)
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}