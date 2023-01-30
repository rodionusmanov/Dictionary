package com.example.dictionary.mvvm.presentation.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppStateMVVM>(
    protected val liveData: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}