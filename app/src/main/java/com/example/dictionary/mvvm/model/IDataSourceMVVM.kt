package com.example.dictionary.mvvm.model

import io.reactivex.rxjava3.core.Observable

interface IDataSourceMVVM<T> {
    fun getData(word: String): Observable<T>
}