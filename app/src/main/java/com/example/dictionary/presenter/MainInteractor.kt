package com.example.dictionary.presenter

import com.example.dictionary.AppState
import com.example.dictionary.DataModel
import com.example.dictionary.interfaces.Interactor
import com.example.dictionary.interfaces.Repository
import io.reactivex.rxjava3.core.Observable

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean):
            Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}