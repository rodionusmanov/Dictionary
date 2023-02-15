package com.example.core.presentation.view

interface InteractorMVVM<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}