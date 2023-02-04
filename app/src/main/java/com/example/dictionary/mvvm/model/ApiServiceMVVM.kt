package com.example.dictionary.mvvm.model

import com.example.dictionary.mvvm.model.data.DataModelMVVM
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMVVM {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModelMVVM>>
}