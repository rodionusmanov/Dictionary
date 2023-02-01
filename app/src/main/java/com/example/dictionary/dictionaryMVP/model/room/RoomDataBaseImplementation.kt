package com.example.dictionary.dictionaryMVP.model.room

import com.example.dictionary.dictionaryMVP.model.data.DataModel
import com.example.dictionary.dictionaryMVP.model.DataSource
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}
