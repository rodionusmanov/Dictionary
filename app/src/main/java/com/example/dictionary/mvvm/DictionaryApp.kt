package com.example.dictionary.mvvm

import android.app.Application
import com.example.dictionary.mvvm.di.koin.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(KoinModules.application, KoinModules.mainScreen, KoinModules.historyScreen)
        }
    }
}