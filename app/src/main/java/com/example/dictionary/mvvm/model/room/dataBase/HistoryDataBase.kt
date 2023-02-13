package com.example.dictionary.mvvm.model.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.mvvm.model.room.dataAccessObject.IHistoryDAO
import com.example.dictionary.mvvm.model.room.entities.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDataBase: RoomDatabase() {

    abstract fun historyDAO(): IHistoryDAO
}