package com.example.dictionary.dictionaryMVP.model.repository

import com.example.dictionary.dictionaryMVP.model.data.DataModel
import com.example.dictionary.dictionaryMVP.model.DataSource
import io.reactivex.rxjava3.core.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
