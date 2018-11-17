package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.TopicListBean

class TopicRespository: BaseRepository(){
    val mDataSource = TopicDataSource()

    fun fetchInitialData(callBack:(MutableList<TopicListBean.Item>)->Unit){
        mDataSource.loadInitial {
            callBack(it)
        }
    }

    fun fetchAfterData(callBack:(MutableList<TopicListBean.Item>)->Unit){
        mDataSource.loadAfter {
            callBack(it)
        }
    }

    fun getLoadDataStatus() = mDataSource.mLoadStatus
}