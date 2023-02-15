package com.example.dictionary.mvvm.model.retrofit

import com.example.dictionary.mvvm.model.ApiServiceMVVM
import com.example.dictionary.mvvm.model.BaseInterceptorMVVM
import com.example.dictionary.mvvm.model.IDataSourceMVVM
import com.example.data.DataModelMVVM
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplMVVM: IDataSourceMVVM<List<DataModelMVVM>> {

    override suspend fun getData(word: String): List<DataModelMVVM> {
        return getService(BaseInterceptorMVVM.interceptor).searchAsync(word).await()
    }

    private fun getService(interceptor: Interceptor): ApiServiceMVVM {
        return createRetrofit(interceptor).create(ApiServiceMVVM::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS =
            "https://dictionary.skyeng.ru/api/public/v1/"
    }
}