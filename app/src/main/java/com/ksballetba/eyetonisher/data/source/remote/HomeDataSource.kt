package com.ksballetba.eyetonisher.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.NetworkState
import com.ksballetba.eyetonisher.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeDataSource {
    var mLoadStatus = MutableLiveData<NetworkState>()
    var mNextPgaeDate = MutableLiveData<String>()
    var mDisposables: CompositeDisposable = CompositeDisposable()
    var mRecoPage:Int = 0

    fun loadInitial(callBack: (MutableList<HomeListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getHomeList("0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mNextPgaeDate.postValue(it.nextPageUrl.substring(55,68))
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


    fun loadAfter(key: String, callBack: (MutableList<HomeListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getHomeList(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mNextPgaeDate.postValue(it.nextPageUrl.substring(55,68))
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

    fun loadRecoInitial(callBack: (MutableList<HomeListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getRecoList(0,RetrofitClient.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mRecoPage = 1
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


    fun loadRecoAfter(callBack: (MutableList<HomeListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        val disposable = RetrofitClient.instance
                .create(ApiService::class.java)
                .getRecoList(mRecoPage,RetrofitClient.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mRecoPage++
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

    fun invalidate() {
        mDisposables.dispose()
    }
}