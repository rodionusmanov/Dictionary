package com.example.dictionary.mvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import com.example.dictionary.mvvm.presentation.viewModel.base.BaseViewModel
import dagger.MapKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class MainViewModel(
    private val interactor: MainInteractorMVVM
) : BaseViewModel<AppStateMVVM>() {

    private val liveData: LiveData<AppStateMVVM> = _mutableLiveData

    fun subscribe(): LiveData<AppStateMVVM> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppStateMVVM.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getData(word, isOnline))
        }

    override fun onCleared() {
        _mutableLiveData.value = AppStateMVVM.Success(null)
        super.onCleared()
    }

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}