package com.example.dictionary.mvvm.model

interface IDataSourceMVVM<T> {
    suspend fun getData(word: String): T
}