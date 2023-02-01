package com.example.dictionary.mvvm.presentation.view

import com.example.dictionary.dictionaryMVP.presenter.Interactor
import com.example.dictionary.mvvm.di.NAME_LOCAL
import com.example.dictionary.mvvm.di.NAME_REMOTE
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractorMVVM @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: IRepositoryMVVM<List<DataModelMVVM>>,
    @Named(NAME_LOCAL) val localRepository: IRepositoryMVVM<List<DataModelMVVM>>
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