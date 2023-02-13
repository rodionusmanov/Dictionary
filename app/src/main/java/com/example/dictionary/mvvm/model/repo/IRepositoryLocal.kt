package com.example.dictionary.mvvm.model.repo

import com.example.dictionary.mvvm.model.data.AppStateMVVM

interface IRepositoryLocal <T> : IRepositoryMVVM<T> {
    suspend fun saveToDB(appState: AppStateMVVM)
}
