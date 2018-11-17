package com.ksballetba.eyetonisher.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient{
    private val DEFAULT_TIMEOUT:Long = 15
    private val BASE_URL = "http://baobab.kaiyanapp.com/api/"
    var mOkHttpClient:OkHttpClient? = null
    var mRetrofit:Retrofit? = null

    init {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("NetError", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC
        mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()
        mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    companion object {
        val vc:Int = 411
        val deviceModel:String = "Nexus%206P"
        val uid:String = "0e03d29034f5474f981d27cded1b2b65c8316d11"

        @Volatile
        var sRetrofitClient:RetrofitClient? = null

        private fun initRetrofitClient():RetrofitClient{
            if(sRetrofitClient==null){
                synchronized(RetrofitClient::class.java){
                    if(sRetrofitClient==null){
                        sRetrofitClient = RetrofitClient()
                    }
                }
            }
            return sRetrofitClient!!
        }
        val instance:Retrofit
        get() = initRetrofitClient().mRetrofit!!
    }
}