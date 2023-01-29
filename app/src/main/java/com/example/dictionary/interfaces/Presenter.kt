package com.example.dictionary.interfaces

import com.example.dictionary.AppState

interface Presenter<T : AppState, V: View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}