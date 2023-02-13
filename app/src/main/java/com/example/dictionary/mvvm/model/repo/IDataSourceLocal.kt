package com.example.dictionary.mvvm.model.repo

import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.AppStateMVVM

interface IDataSourceLocal<T> : IDataSourceMVVM<T> {
    suspend fun saveToDB(appState: AppStateMVVM)
}
