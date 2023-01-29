package com.example.dictionary.repository

import com.example.dictionary.DataModel
import com.example.dictionary.interfaces.DataSource
import io.reactivex.rxjava3.core.Observable

class DataSourceRemote (private val remoteProvider: RetrofitImplementation =
                            RetrofitImplementation()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation =
                          RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}