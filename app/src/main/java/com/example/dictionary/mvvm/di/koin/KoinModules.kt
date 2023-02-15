package com.example.dictionary.mvvm.di.koin

import androidx.room.Room
import com.example.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IRepositoryLocal
import com.example.dictionary.mvvm.model.repo.IRepositoryMVVM
import com.example.dictionary.mvvm.model.repo.RepositoryImplLocalMVVM
import com.example.dictionary.mvvm.model.repo.RepositoryImplMVVM
import com.example.dictionary.mvvm.model.retrofit.RetrofitImplMVVM
import com.example.dictionary.mvvm.model.room.RoomDataBaseImplMVVM
import com.example.dictionary.mvvm.model.room.dataBase.HistoryDataBase
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import com.example.dictionary.mvvm.presentation.view.history.HistoryInteractor
import com.example.dictionary.mvvm.presentation.viewModel.MainViewModel
import com.example.dictionary.mvvm.presentation.viewModel.history.HistoryViewModel
import org.koin.dsl.module

object KoinModules {
    val application = module {
        single {
            Room.databaseBuilder(
                get(), HistoryDataBase::class.java,
                "HistoryDB"
            ).build()
        }
        single { get<HistoryDataBase>().historyDAO() }
        single<IRepositoryMVVM<List<DataModelMVVM>>> {
            RepositoryImplMVVM(RetrofitImplMVVM())
        }
        single<IRepositoryLocal<List<DataModelMVVM>>> {
            RepositoryImplLocalMVVM(RoomDataBaseImplMVVM(get()))
        }
    }

    val mainScreen = module {
        factory { MainViewModel(get()) }
        factory {
            MainInteractorMVVM(
                get(),
                get()
            )
        }
    }

    val historyScreen = module {
        factory { HistoryViewModel(get()) }
        factory { HistoryInteractor(get(), get()) }
    }
}