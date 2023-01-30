package com.example.dictionary.dictionaryMVP.presenter

import com.example.dictionary.dictionaryMVP.model.data.AppState
import com.example.dictionary.dictionaryMVP.view.base.View

interface Presenter<T : AppState, V: View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}