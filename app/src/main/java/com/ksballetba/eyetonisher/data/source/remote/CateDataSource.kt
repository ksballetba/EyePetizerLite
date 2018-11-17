package com.ksballetba.eyetonisher.data.source.remote

import android.util.Log
import com.ksballetba.eyetonisher.data.bean.CateListBean
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CateDataSource{
    fun loadInitial(callBack: (MutableList<CateListBean.Item>) -> Unit) {
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCateList()
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
                            Log.d("debug","网络错误")
                        }
                )
    }
}