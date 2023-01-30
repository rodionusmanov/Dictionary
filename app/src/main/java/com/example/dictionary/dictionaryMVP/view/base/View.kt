package com.example.dictionary.dictionaryMVP.view.base

import com.example.dictionary.dictionaryMVP.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}