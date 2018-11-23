package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.source.remote.HomeRespository

class HomeViewModel constructor(private var homeRespository: HomeRespository):BaseViewModel(homeRespository){
    fun fetchHomeData():LiveData<MutableList<HomeListBean.Item>>{
        val result = MutableLiveData<MutableList<HomeListBean.Item>>()
        homeRespository.fetchInitialHomeData {
            result.postValue(it)
        }
        return result
    }

    fun fetchHomeDataAfter(date:String):LiveData<MutableList<HomeListBean.Item>>{
        val result = MutableLiveData<MutableList<HomeListBean.Item>>()
        homeRespository.fetchAfterHomeData(date) {
            result.postValue(it)
        }
        return result
    }

    fun fetchRecoData():LiveData<MutableList<HomeListBean.Item>>{
        val result = MutableLiveData<MutableList<HomeListBean.Item>>()
        homeRespository.fetchInitialRecoData {
            result.postValue(it)
        }
        return result
    }

    fun fetchRecoDataAfter():LiveData<MutableList<HomeListBean.Item>>{
        val result = MutableLiveData<MutableList<HomeListBean.Item>>()
        homeRespository.fetchAfterRecoData {
            result.postValue(it)
        }
        return result
    }

    fun fetchLoadStatus() = homeRespository.getLoadDataStatus()

    fun getNextPageUrl() = homeRespository.getNextPageUrl()

}