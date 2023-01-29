package com.example.dictionary.repository

import com.example.dictionary.DataModel
import com.example.dictionary.interfaces.DataSource
import com.example.dictionary.interfaces.Repository
import io.reactivex.rxjava3.core.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
