package com.example.dictionary.mvvm.presentation.view.base

import com.example.dictionary.dictionaryMVP.model.data.AppState

interface ViewMVVM {
    fun renderData(appState: AppState)
}