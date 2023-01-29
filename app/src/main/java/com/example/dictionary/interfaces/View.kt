package com.example.dictionary.interfaces

import com.example.dictionary.AppState

interface View {
    fun renderData(appState: AppState)
}