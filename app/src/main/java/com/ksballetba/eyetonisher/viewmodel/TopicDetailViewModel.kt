package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.TopicInfoBean
import com.ksballetba.eyetonisher.data.source.remote.TopicDetailRespository

class TopicDetailViewModel(private val topicDetailRespository: TopicDetailRespository):BaseViewModel(topicDetailRespository){
    fun fetchData():LiveData<TopicInfoBean>{
        val result = MutableLiveData<TopicInfoBean>()
        topicDetailRespository.fetchData {
            result.postValue(it)
        }
        return result
    }
}