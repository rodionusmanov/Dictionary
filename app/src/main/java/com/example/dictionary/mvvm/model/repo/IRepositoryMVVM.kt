package com.example.dictionary.mvvm.model.repo

import io.reactivex.rxjava3.core.Observable

interface IRepositoryMVVM<T> {
    fun getData(word: String): Observable<T>
}