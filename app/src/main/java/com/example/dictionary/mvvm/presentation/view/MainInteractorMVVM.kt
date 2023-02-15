package com.example.dictionary.mvvm.presentation.view

import com.example.core.presentation.view.InteractorMVVM
import com.example.data.AppStateMVVM
import com.example.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryLocal
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM

class MainInteractorMVVM constructor(
    private val remoteRepository: IRepositoryMVVM<List<DataModelMVVM>>,
    private val localRepository: IRepositoryLocal<List<DataModelMVVM>>
) : InteractorMVVM<AppStateMVVM> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppStateMVVM {val appState: AppStateMVVM
        if (fromRemoteSource) {
            appState = AppStateMVVM.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppStateMVVM.Success(localRepository.getData(word))
        }
        return appState
    }
}
