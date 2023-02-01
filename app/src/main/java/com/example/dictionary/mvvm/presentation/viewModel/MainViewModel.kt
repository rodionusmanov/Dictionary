package com.example.dictionary.mvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.mvvm.model.data.AppStateMVVM
import com.example.dictionary.mvvm.presentation.view.MainInteractorMVVM
import com.example.dictionary.mvvm.presentation.viewModel.base.BaseViewModel
import dagger.MapKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlin.reflect.KClass

class MainViewModel @Inject constructor(
    private val interactor: MainInteractorMVVM
) : BaseViewModel<AppStateMVVM>() {

    private var appStateMVVM: AppStateMVVM? = null

    fun subscribe(): LiveData<AppStateMVVM>{
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean): LiveData<AppStateMVVM> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.value = AppStateMVVM.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveData.value = AppStateMVVM.Loading(null) }

    private fun getObserver(): DisposableObserver<AppStateMVVM> {
        return object : DisposableObserver<AppStateMVVM>() {
            override fun onNext(t: AppStateMVVM) {
                appStateMVVM = t
                liveData.value = t
            }

            override fun onError(e: Throwable) {
                liveData.value = AppStateMVVM.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}