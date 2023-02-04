package com.example.dictionary.mvvm.di.rx

import android.app.Application
import com.example.dictionary.mvvm.DictionaryApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepoModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(dictionaryApp: DictionaryApp)
}