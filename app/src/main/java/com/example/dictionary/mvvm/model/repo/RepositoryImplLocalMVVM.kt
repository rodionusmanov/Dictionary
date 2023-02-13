package com.example.dictionary.mvvm.model.repo

import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM

class RepositoryImplLocalMVVM(private val dataSource: IDataSourceLocal<List<DataModelMVVM>>) :
    IRepositoryLocal<List<DataModelMVVM>> {

    override suspend fun getData(word: String): List<DataModelMVVM> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppStateMVVM) {
        dataSource.saveToDB(appState)
    }
}
