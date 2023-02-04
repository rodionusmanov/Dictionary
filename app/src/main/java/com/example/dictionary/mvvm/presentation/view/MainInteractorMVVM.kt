package com.example.dictionary.mvvm.presentation.view

import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM

class MainInteractorMVVM constructor(
    private val remoteRepository: IRepositoryMVVM<List<DataModelMVVM>>,
    private val localRepository: IRepositoryMVVM<List<DataModelMVVM>>
) : InteractorMVVM<AppStateMVVM> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppStateMVVM {
        return AppStateMVVM.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}