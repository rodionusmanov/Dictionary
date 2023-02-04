package com.example.dictionary.mvvm.model

import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.retrofit.RetrofitImplMVVM
import com.example.dictionary.mvvm.model.room.RoomDataBaseImplMVVM

class DataSourceRemoteMVVM (private val remoteProvider: RetrofitImplMVVM =
                            RetrofitImplMVVM()
) :
    IDataSourceMVVM<List<DataModelMVVM>> {
    override suspend fun getData(word: String): List<DataModelMVVM> =
        remoteProvider.getData(word)
}

class DataSourceLocalMVVM(private val remoteProvider: RoomDataBaseImplMVVM =
                          RoomDataBaseImplMVVM()
) :
    IDataSourceMVVM<List<DataModelMVVM>> {
    override suspend fun getData(word: String):List<DataModelMVVM> =
        remoteProvider.getData(word)
}