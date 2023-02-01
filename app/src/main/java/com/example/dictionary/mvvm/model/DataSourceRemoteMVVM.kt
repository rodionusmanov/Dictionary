package com.example.dictionary.mvvm.model

import com.example.dictionary.mvvm.model.room.RoomDataBaseImplMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.retrofit.RetrofitImplMVVM
import io.reactivex.rxjava3.core.Observable

class DataSourceRemoteMVVM (private val remoteProvider: RetrofitImplMVVM =
                            RetrofitImplMVVM()
) :
    IDataSourceMVVM<List<DataModelMVVM>> {
    override fun getData(word: String): Observable<List<DataModelMVVM>> =
        remoteProvider.getData(word)
}

class DataSourceLocalMVVM(private val remoteProvider: RoomDataBaseImplMVVM =
                          RoomDataBaseImplMVVM()
) :
    IDataSourceMVVM<List<DataModelMVVM>> {
    override fun getData(word: String): Observable<List<DataModelMVVM>> =
        remoteProvider.getData(word)
}