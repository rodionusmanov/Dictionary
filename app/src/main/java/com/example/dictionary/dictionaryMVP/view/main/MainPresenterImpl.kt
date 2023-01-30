package com.example.dictionary.dictionaryMVP.view.main

import com.example.dictionary.dictionaryMVP.model.data.AppState
import com.example.dictionary.dictionaryMVP.view.base.View
import com.example.dictionary.dictionaryMVP.model.DataSourceLocal
import com.example.dictionary.dictionaryMVP.model.DataSourceRemote
import com.example.dictionary.dictionaryMVP.model.repository.RepositoryImplementation
import com.example.dictionary.dictionaryMVP.presenter.Presenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenterImpl<T : AppState, V : View>(

    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView){
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }
            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }
            override fun onComplete() {
            }
        }
    }
}