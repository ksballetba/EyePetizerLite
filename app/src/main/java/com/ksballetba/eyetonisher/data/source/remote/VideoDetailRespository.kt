package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.RelatedVideoBean
import com.ksballetba.eyetonisher.data.bean.RepliesBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean

class VideoDetailRespository constructor(val videoId: Int):BaseRepository(){

    val mDataSource = VideoDetailDataSource(videoId)

    fun fetchInfo(callBack: (VideoInfoBean) -> Unit) {
        mDataSource.loadInfo{
            callBack(it)
        }
    }

    fun fetchReplies(callBack: (MutableList<RepliesBean.Item>) -> Unit) {
        mDataSource.loadReplies{
            callBack(it)
        }
    }

    fun fetchRelatedVideos(callBack: (MutableList<RelatedVideoBean.Item>) -> Unit) {
        mDataSource.loadRelatedVideos{
            callBack(it)
        }
    }
}