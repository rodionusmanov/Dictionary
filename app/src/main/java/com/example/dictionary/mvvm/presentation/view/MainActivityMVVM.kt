package com.example.dictionary.mvvm.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.presentation.view.base.BaseActivityMVVM
import com.example.dictionary.mvvm.presentation.viewModel.MainViewModel
import com.example.dictionary.mvvm.presentation.viewModel.adapter.MainAdapterMVVM

class MainActivityMVVM : BaseActivityMVVM<AppStateMVVM>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapterMVVM? = null
    private val onListItemClickListener: MainAdapterMVVM.OnListItemClickListener =
        object : MainAdapterMVVM.OnListItemClickListener {
            override fun onItemClick(data: DataModelMVVM) {
                Toast.makeText(
                    this@MainActivityMVVM, data.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val observer = Observer<AppStateMVVM> {
        renderData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragmentMVVM.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragmentMVVM.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord, true).observe(this@MainActivityMVVM, observer)
                }
            })
            searchDialogFragment.show(
                supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }
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
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppStateMVVM.Loading -> {
                showViewLoading()
                if (appStateMVVM.progress != null) {
                    binding.progressBarHorizontal.visibility = android.view.View.VISIBLE
                    binding.progressBarRound.visibility = android.view.View.GONE
                    binding.progressBarHorizontal.progress = appStateMVVM.progress
                } else {
                    binding.progressBarHorizontal.visibility = android.view.View.GONE
                    binding.progressBarRound.visibility = android.view.View.VISIBLE
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
            viewModel.getData("hi", true).observe(this, observer)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = android.view.View.VISIBLE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.VISIBLE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}