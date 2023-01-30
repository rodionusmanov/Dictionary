package com.example.dictionary.mvvm.presentation.view

import com.example.dictionary.dictionaryMVP.presenter.Interactor
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import io.reactivex.rxjava3.core.Observable

class MainInteractorMVVM (
    private val remoteRepository: IRepositoryMVVM<List<DataModelMVVM>>,
    private val localRepository: IRepositoryMVVM<List<DataModelMVVM>>
) : Interactor<AppStateMVVM> {
    override fun getData(word: String, fromRemoteSource: Boolean):
            Observable<AppStateMVVM> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppStateMVVM.Success(it) }
        } else {
            localRepository.getData(word).map { AppStateMVVM.Success(it) }
        }
    }
}