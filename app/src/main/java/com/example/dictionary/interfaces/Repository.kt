package com.example.dictionary.interfaces

import io.reactivex.rxjava3.core.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}