package com.example.dictionary.mvvm.presentation.viewModel

import com.example.dictionary.mvvm.model.data.AppStateMVVM

interface IMainViewModel<T: AppStateMVVM> {

    fun getData(word: String, isOnline: Boolean)
}