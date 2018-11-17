package com.ksballetba.eyetonisher.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.data.bean.TopicListBean
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.NetworkState
import com.ksballetba.eyetonisher.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TopicDataSource{
    companion object {
        const val DEF_PAGE_SIZE = 10
    }
    var mLoadStatus = MutableLiveData<NetworkState>()
    var mNextPgaeKey = 10
    var mDisposables: CompositeDisposable = CompositeDisposable()

    fun loadInitial(callBack: (MutableList<TopicListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getTopicList(0, DEF_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mNextPgaeKey = 10
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")
                        },
                        onError = {
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
        mDisposables.add(disposable)
    }


    fun loadAfter(callBack: (MutableList<TopicListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getTopicList(mNextPgaeKey, DEF_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mNextPgaeKey+=10
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
        mDisposables.add(disposable)
    }
}