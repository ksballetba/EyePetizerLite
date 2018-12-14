package com.ksballetba.eyetonisher.utilities

import android.os.Environment
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.ksballetba.eyetonisher.data.bean.CateListBean
import com.ksballetba.eyetonisher.data.source.remote.ProgressDownloadInterceptor
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.RetrofitClient
import com.ksballetba.eyetonisher.services.DownloadService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.TimeUnit

class DownloadUtils(val downloadListener: DownloadService.DownloadListener) {

    private val DEFAULT_TIMEOUT:Long = 15
    private val BASE_URL = "http://baobab.kaiyanapp.com/api/"
    private var mOkHttpClient: OkHttpClient? = null
    private var mRetrofit: Retrofit? = null

    init {
        val progressDownloadInterceptor = ProgressDownloadInterceptor(downloadListener)
        mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(progressDownloadInterceptor)
                .build()
        mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    fun downloadVideo(url:String,fileName: String) {
        mRetrofit!!
                .create(ApiService::class.java)
                .downloadVideo(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map {
                    it.byteStream()
                }
                .observeOn(Schedulers.computation())
                .doOnNext {
                    writeToFile(fileName,it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {

                        },
                        onComplete = {
                            downloadListener.onSuccess()
                        },
                        onError = {
                            downloadListener.onFailed()
                            Log.d("debug",it.message)
                        }
                )
    }

    private fun writeToFile(fileName:String,inputStream:InputStream){
        val fileDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).path+"/eyetonisher/")
        if(!fileDir.exists()){
            fileDir.mkdir()
        }
        val file = File(fileDir,"$fileName.mp4")
        if(file.exists()){
            file.delete()
        }
        var fos:FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            var b = ByteArray(1024)
            var len:Int = 0
            while ((inputStream.read(b).apply {
                        len = this
                    }!=-1)) {
                fos.write(b, 0, len)
            }
            inputStream.close()
            fos.close()
        } catch (e:FileNotFoundException){
            downloadListener.onFailed()
            Log.d("debug",e.toString())
        } catch (e:IOException){
            downloadListener.onFailed()
            Log.d("debug",e.toString())

        }
    }

    companion object {

        @Volatile
        var sDownloadManager:DownloadUtils? = null

        private fun initRetrofitClient(downloadListener: DownloadService.DownloadListener):DownloadUtils{
            if(sDownloadManager==null){
                synchronized(RetrofitClient::class.java){
                    if(sDownloadManager==null){
                        sDownloadManager = DownloadUtils(downloadListener)
                    }
                }
            }
            return sDownloadManager!!
        }

        fun getInstance(downloadListener: DownloadService.DownloadListener)
            = initRetrofitClient(downloadListener)
    }
}