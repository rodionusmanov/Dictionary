package com.example.dictionary.mvvm.utils

import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.data.Meanings
import com.example.dictionary.mvvm.model.room.entities.HistoryEntity

fun parseOnlineSearchResults(appState: AppStateMVVM): AppStateMVVM {
    return AppStateMVVM.Success(mapResult(appState, true))
}
fun parseLocalSearchResults(appState: AppStateMVVM): AppStateMVVM {
    return AppStateMVVM.Success(mapResult(appState, false))
}
private fun mapResult(
    appState: AppStateMVVM,
    isOnline: Boolean
): List<DataModelMVVM> {
    val newSearchResults = arrayListOf<DataModelMVVM>()
    when (appState) {
        is AppStateMVVM.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {}
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppStateMVVM.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModelMVVM>
) {
    val dataModels: List<DataModelMVVM> = appState.data as List<DataModelMVVM>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModelMVVM(searchResult.text, arrayListOf()))
            }
        }
    }
}
private fun parseOnlineResult(dataModel: DataModelMVVM, newDataModels:
ArrayList<DataModelMVVM>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null &&
                !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModelMVVM(dataModel.text, newMeanings))
        }
    }
}
fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModelMVVM> {
    val searchResult = ArrayList<DataModelMVVM>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModelMVVM(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppStateMVVM): HistoryEntity? {
    return when (appState) {
        is AppStateMVVM.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty())
            {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}
fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}
