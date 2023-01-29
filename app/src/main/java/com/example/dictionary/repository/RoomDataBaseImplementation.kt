package com.example.dictionary.repository

import com.example.dictionary.DataModel
import com.example.dictionary.interfaces.DataSource
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}
