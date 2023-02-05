package com.example.dictionary.mvvm.model.repo

interface IRepositoryMVVM<T> {
    suspend fun getData(word: String): T
}