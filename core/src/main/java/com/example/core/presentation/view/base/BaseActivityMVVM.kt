package com.example.core.presentation.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.data.AppStateMVVM
import com.example.core.presentation.viewModel.base.BaseViewModel

abstract class BaseActivityMVVM<T: AppStateMVVM>: AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appStateMVVM: T)
}