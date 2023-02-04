package com.example.dictionary.mvvm.di.rx

import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repoRemote: IRepositoryMVVM<List<DataModelMVVM>>,
        @Named(NAME_LOCAL) repoLocal: IRepositoryMVVM<List<DataModelMVVM>>
    ) = MainInteractorMVVM(repoRemote, repoLocal)
}