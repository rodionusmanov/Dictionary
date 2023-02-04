package com.example.dictionary.mvvm.di.rx

import com.example.dictionary.mvvm.presentation.view.MainActivityMVVM
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivityMVVM
}