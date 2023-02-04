package com.example.dictionary.mvvm.di.rx

import com.example.dictionary.mvvm.model.room.RoomDataBaseImplMVVM
import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import com.example.dictionary.mvvm.model.repo.RepositoryImplMVVM
import com.example.dictionary.mvvm.model.retrofit.RetrofitImplMVVM
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepoRemote(
        @Named(NAME_REMOTE) dataSourceRemote: IDataSourceMVVM<List<DataModelMVVM>>
    ): IRepositoryMVVM<List<DataModelMVVM>> =
        RepositoryImplMVVM(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepoLocal(
        @Named(NAME_LOCAL) dataSourceLocal: IDataSourceMVVM<List<DataModelMVVM>>
    ): IRepositoryMVVM<List<DataModelMVVM>> =
        RepositoryImplMVVM(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): IDataSourceMVVM<List<DataModelMVVM>> =
        RetrofitImplMVVM()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): IDataSourceMVVM<List<DataModelMVVM>> =
        RoomDataBaseImplMVVM()
}