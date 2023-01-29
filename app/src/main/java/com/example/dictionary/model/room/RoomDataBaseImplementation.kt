package com.example.dictionary.model.room

import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.DataSource
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}
