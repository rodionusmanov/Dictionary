package com.example.dictionary.mvvm.model.room

import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM

class RoomDataBaseImplMVVM : IDataSourceMVVM<List<DataModelMVVM>> {
    override suspend fun getData(word: String): List<DataModelMVVM> {
        TODO("not implemented")
    }
}
