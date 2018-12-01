package com.ksballetba.eyetonisher.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.data.bean.CategoryHomeListBean
import com.ksballetba.eyetonisher.data.bean.CategotyPlaylistBean
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.network.ApiService
import com.ksballetba.eyetonisher.network.NetworkState
import com.ksballetba.eyetonisher.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CategoryDetailDataSource constructor(val id: Int){
    companion object {
        const val DEF_VIDEO_PAGE_SIZE = 10
        const val DEF_PLAYLIST_PAGE_SIZE = 5
        const val DEF_PROVIDER_PAGE_SIZE = 5
    }

    var mLoadStatus = MutableLiveData<NetworkState>()
    var mVideoNextPageKey = 10
    var mPlaylistNextPageKey = 5
    var mProviderNextPageKey = 5

    fun loadHotVideoList(callBack: (MutableList<CategoryHomeListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryHotVideoList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")
                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }


    fun loadAllVideoListInitial(callBack: (MutableList<RankListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryAllVideoList(id,0, DEF_VIDEO_PAGE_SIZE,RetrofitClient.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mVideoNextPageKey=10
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }

    fun loadPlaylistInitial(callBack: (MutableList<CategotyPlaylistBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryPlaylist(id,0, DEF_PLAYLIST_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mPlaylistNextPageKey=5
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }

    fun loadProviderListInitial(callBack: (MutableList<CategotyPlaylistBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryProviders(id,0, DEF_PROVIDER_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mProviderNextPageKey=5
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }

    fun loadAllVideoListAfter(callBack: (MutableList<RankListBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryAllVideoList(id,mVideoNextPageKey, DEF_VIDEO_PAGE_SIZE,RetrofitClient.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mVideoNextPageKey+=10
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }

    fun loadPlaylistAfter(callBack: (MutableList<CategotyPlaylistBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryPlaylist(id,mPlaylistNextPageKey, DEF_PLAYLIST_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mPlaylistNextPageKey+=5
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }

    fun loadProviderListAfter(callBack: (MutableList<CategotyPlaylistBean.Item>) -> Unit) {
        mLoadStatus.postValue(NetworkState.LOADING)
        RetrofitClient.instance
                .create(ApiService::class.java)
                .getCategoryProviders(id,mProviderNextPageKey, DEF_PROVIDER_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            callBack(it.itemList.toMutableList())
                            mProviderNextPageKey+=5
                            mLoadStatus.postValue(NetworkState.LOADED)
                        },
                        onComplete = {
                            println("Completed")

                        },
                        onError = {
                            Log.d("debug",it.message)
                            mLoadStatus.postValue(NetworkState.error("网络加载失败"))
                        }
                )
    }
}