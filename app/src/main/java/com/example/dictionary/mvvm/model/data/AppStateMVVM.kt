package com.example.dictionary.mvvm.model.data

sealed class AppStateMVVM {
    data class Success(val data: List<DataModelMVVM>?) : AppStateMVVM()
    data class Error(val error: Throwable) : AppStateMVVM()
    data class Loading(val progress: Int?) : AppStateMVVM()
}
