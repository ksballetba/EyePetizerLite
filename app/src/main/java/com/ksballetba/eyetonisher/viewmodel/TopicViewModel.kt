package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.TopicListBean
import com.ksballetba.eyetonisher.data.source.remote.TopicRespository

class TopicViewModel constructor(private var topicRespository: TopicRespository): BaseViewModel(topicRespository){

    fun fetchData(): LiveData<MutableList<TopicListBean.Item>> {
        val result = MutableLiveData<MutableList<TopicListBean.Item>>()
        topicRespository.fetchInitialData {
            result.postValue(it)
        }
        return result
    }

    fun fetchDataAfter(): LiveData<MutableList<TopicListBean.Item>> {
        val result = MutableLiveData<MutableList<TopicListBean.Item>>()
        topicRespository.fetchAfterData {
            result.postValue(it)
        }
        return result
    }

    fun fetchLoadStatus() = topicRespository.getLoadDataStatus()
}