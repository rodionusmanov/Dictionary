package com.example.dictionary.mvvm.model.repo

import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM

class RepositoryImplMVVM(private val dataSource: IDataSourceMVVM<List<DataModelMVVM>>) :
    IRepositoryMVVM<List<DataModelMVVM>> {
    override suspend fun getData(word: String): List<DataModelMVVM> {
        return dataSource.getData(word)
    }
}
