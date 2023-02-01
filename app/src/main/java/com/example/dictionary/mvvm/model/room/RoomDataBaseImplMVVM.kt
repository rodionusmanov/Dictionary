package com.example.dictionary.mvvm.model.room

import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplMVVM : IDataSourceMVVM<List<DataModelMVVM>> {
    override fun getData(word: String): Observable<List<DataModelMVVM>> {
        TODO("not implemented")
    }
}
