package com.example.dictionary.mvvm.presentation.view

interface InteractorMVVM<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}