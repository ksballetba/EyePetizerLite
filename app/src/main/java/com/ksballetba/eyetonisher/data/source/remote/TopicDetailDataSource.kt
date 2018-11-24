package com.ksballetba.eyetonisher.data.source.remote

import android.util.Log
import com.ksballetba.eyetonisher.data.bean.TopicInfoBean
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.RetrofitClient
import com.ksballetba.eyetonisher.ui.widgets.HeaderItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TopicDetailDataSource constructor(val id:Int){

    fun loadData(callback:(TopicInfoBean)->Unit){
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getTopicDeatail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callback(it)
                        },
                        onComplete = {
                            println("Completed")
                        },
                        onError = {
                            Log.d("debug",it.message)
                        }
                )
    }

}