package com.example.dictionary.mvvm.presentation.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.presentation.viewModel.base.BaseViewModel

abstract class BaseActivityMVVM<T: AppStateMVVM>: AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appStateMVVM: T)
}