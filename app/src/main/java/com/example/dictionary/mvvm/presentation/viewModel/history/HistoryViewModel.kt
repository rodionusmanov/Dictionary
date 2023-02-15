package com.example.dictionary.mvvm.presentation.viewModel.history

import androidx.lifecycle.LiveData
import com.example.data.AppStateMVVM
import com.example.dictionary.mvvm.presentation.view.history.HistoryInteractor
import com.example.dictionary.mvvm.presentation.viewModel.base.BaseViewModel
import com.example.dictionary.mvvm.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppStateMVVM>() {

    private val liveDataForViewToObserve: LiveData<AppStateMVVM> = _mutableLiveData
    fun subscribe(): LiveData<AppStateMVVM> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppStateMVVM.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(
            parseLocalSearchResults(
                interactor.getData(
                    word,
                    isOnline
                )
            )
        )
    }

    override fun onCleared() {
        _mutableLiveData.value = AppStateMVVM.Success(null)
        super.onCleared()
    }
}
