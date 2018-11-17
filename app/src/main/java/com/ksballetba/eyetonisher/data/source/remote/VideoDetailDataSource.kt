package com.ksballetba.eyetonisher.data.source.remote

import android.util.Log
import com.ksballetba.eyetonisher.data.bean.*
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class VideoDetailDataSource constructor(val videoId: Int){

    fun loadInfo(callBack: (VideoInfoBean) -> Unit) {
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getVideoInfo(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it)
                        },
                        onComplete = {
                            println("Completed")
                        },
                        onError = {
                            Log.d("debug","网络错误")
                        }
                )
    }

    fun loadReplies(callBack: (MutableList<RepliesBean.Item>) -> Unit) {
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getVideoReplies(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                        },
                        onComplete = {
                            println("Completed")
                        },
                        onError = {
                            Log.d("debug",it.message)
                        }
                )
    }

    fun loadRelatedVideos(callBack: (MutableList<RelatedVideoBean.Item>) -> Unit) {
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getRelatedList(videoId,RetrofitClient.vc,RetrofitClient.deviceModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
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