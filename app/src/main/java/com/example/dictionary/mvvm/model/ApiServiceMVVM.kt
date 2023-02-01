package com.example.dictionary.mvvm.model

import com.example.dictionary.mvvm.model.data.DataModelMVVM
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMVVM {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModelMVVM>>
}