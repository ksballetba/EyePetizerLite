package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.data.source.remote.RankRespository

class RankViewModel constructor(private var rankRespository: RankRespository): BaseViewModel(rankRespository){

    fun fetchData(): LiveData<MutableList<RankListBean.Item>> {
        val result = MutableLiveData<MutableList<RankListBean.Item>>()
        rankRespository.fetchInitialData {
            result.postValue(it)
        }
        return result
    }

    fun fetchDataAfter(): LiveData<MutableList<RankListBean.Item>> {
        val result = MutableLiveData<MutableList<RankListBean.Item>>()
        rankRespository.fetchAfterData {
            result.postValue(it)
        }
        return result
    }

    fun fetchLoadStatus() = rankRespository.getLoadDataStatus()
}