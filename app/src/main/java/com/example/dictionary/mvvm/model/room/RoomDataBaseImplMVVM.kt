package com.example.dictionary.mvvm.model.room

import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.model.data.DataModelMVVM
import com.example.dictionary.mvvm.model.repo.IDataSourceLocal
import com.example.dictionary.mvvm.model.room.dataAccessObject.IHistoryDAO
import com.example.dictionary.mvvm.model.room.entities.HistoryEntity

class RoomDataBaseImplMVVM(private val historyDAO: IHistoryDAO) :
    IDataSourceLocal<List<DataModelMVVM>> {

    override suspend fun getData(word: String): List<DataModelMVVM> {
        return mapHistoryEntityToSearchResult(historyDAO.all())
    }

    override suspend fun saveToDB(appState: AppStateMVVM) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDAO.insert(it)
        }
    }

    fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModelMVVM> {
        val dataModel = ArrayList<DataModelMVVM>()
        if (!list.isNullOrEmpty()) {
            for (entity in list) {
                dataModel.add(DataModelMVVM(entity.word, null))
            }
        }
        return dataModel
    }

    fun convertDataModelSuccessToEntity(appState: AppStateMVVM): HistoryEntity? {
        return when (appState) {
            is AppStateMVVM.Success -> {
                val searchResult = appState.data
                if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                    null
                } else {
                    HistoryEntity(searchResult[0].text!!, null)
                }
            }
            else -> null
        }
    }
}
