package com.example.dictionary.mvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import com.example.dictionary.mvvm.model.DataSourceLocalMVVM
import com.example.dictionary.mvvm.model.DataSourceRemoteMVVM
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.repo.RepositoryImplMVVM
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import com.example.dictionary.mvvm.presentation.viewModel.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val interactor: MainInteractorMVVM = MainInteractorMVVM(
        RepositoryImplMVVM(DataSourceRemoteMVVM()),
        RepositoryImplMVVM(DataSourceLocalMVVM())
    )
) : BaseViewModel<AppStateMVVM>() {

    private var appStateMVVM: AppStateMVVM? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppStateMVVM> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.value = AppStateMVVM.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppStateMVVM> {
        return object : DisposableObserver<AppStateMVVM>() {
            override fun onNext(t: AppStateMVVM) {
                appStateMVVM = t
                liveData.value = t
            }

            override fun onError(e: Throwable) {
                liveData.value = AppStateMVVM.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}