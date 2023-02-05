package com.example.dictionary.mvvm.di.koin

import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import com.example.dictionary.mvvm.model.repo.RepositoryImplMVVM
import com.example.dictionary.mvvm.model.retrofit.RetrofitImplMVVM
import com.example.dictionary.mvvm.model.room.RoomDataBaseImplMVVM
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import com.example.dictionary.mvvm.presentation.viewModel.MainViewModel
import com.example.dictionary.mvvm.utils.NAME_LOCAL
import com.example.dictionary.mvvm.utils.NAME_REMOTE
import org.koin.core.qualifier.named
import org.koin.dsl.module

object KoinModules {
    val application = module {
        single<IRepositoryMVVM<List<DataModelMVVM>>>(named(NAME_REMOTE)) {
            RepositoryImplMVVM(RetrofitImplMVVM())
        }
        single<IRepositoryMVVM<List<DataModelMVVM>>>(named(NAME_LOCAL)) {
            RepositoryImplMVVM(RoomDataBaseImplMVVM())
        }
    }

    val mainScreen = module {
        factory {
            MainInteractorMVVM(get(named(NAME_REMOTE)),
            get(named(NAME_LOCAL)))
        }
        factory { MainViewModel(get()) }
    }
}