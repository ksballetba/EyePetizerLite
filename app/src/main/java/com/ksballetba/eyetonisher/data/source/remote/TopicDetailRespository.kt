package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.TopicInfoBean

class TopicDetailRespository constructor(val id:Int):BaseRepository(){
    val mDataSource = TopicDetailDataSource(id)

    fun fetchData(callback:(TopicInfoBean)->Unit){
        mDataSource.loadData{
            callback(it)
        }
    }
}