package com.example.dictionary.mvvm.model.repo

import com.example.dictionary.mvvm.model.DataSourceLocalMVVM
import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import io.reactivex.rxjava3.core.Observable

class RepositoryImplMVVM(private val dataSource: IDataSourceMVVM<List<DataModelMVVM>>) :
    IRepositoryMVVM<List<DataModelMVVM>> {
    override fun getData(word: String): Observable<List<DataModelMVVM>> {
        return dataSource.getData(word)
    }
}
