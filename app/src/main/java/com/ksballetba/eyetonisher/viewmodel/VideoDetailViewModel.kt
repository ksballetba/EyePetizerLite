package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.RelatedVideoBean
import com.ksballetba.eyetonisher.data.bean.RepliesBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import com.ksballetba.eyetonisher.data.source.remote.VideoDetailRespository

class VideoDetailViewModel constructor(private var videoDetailRespository: VideoDetailRespository): BaseViewModel(videoDetailRespository){

    fun fetchInfo(): LiveData<VideoInfoBean> {
        val result = MutableLiveData<VideoInfoBean>()
        videoDetailRespository.fetchInfo{
            result.postValue(it)
        }
        return result
    }

    fun fetchReplies(): LiveData<MutableList<RepliesBean.Item>> {
        val result = MutableLiveData<MutableList<RepliesBean.Item>>()
        videoDetailRespository.fetchReplies {
            result.postValue(it)
        }
        return result
    }

    fun fetchRelatedVideo():LiveData<MutableList<RelatedVideoBean.Item>>{
        val result = MutableLiveData<MutableList<RelatedVideoBean.Item>>()
        videoDetailRespository.fetchRelatedVideos {
            result.postValue(it)
        }
        return result
    }
}