package com.example.dictionary.dictionaryMVP.model.room

import com.example.dictionary.dictionaryMVP.model.DataSource
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplMVVM : DataSource<List<DataModelMVVM>> {
    override fun getData(word: String): Observable<List<DataModelMVVM>> {
        TODO("not implemented")
    }
}
