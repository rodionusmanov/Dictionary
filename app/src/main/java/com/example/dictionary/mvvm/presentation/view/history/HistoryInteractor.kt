package com.example.dictionary.mvvm.presentation.view.history

import com.example.data.AppStateMVVM
import com.example.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryLocal
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import com.example.core.presentation.view.InteractorMVVM

class HistoryInteractor(
    private val repositoryRemote: IRepositoryMVVM<List<DataModelMVVM>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModelMVVM>>
) : InteractorMVVM<AppStateMVVM> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppStateMVVM{
        return AppStateMVVM.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
