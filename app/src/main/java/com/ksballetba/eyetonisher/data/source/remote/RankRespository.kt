package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.RankListBean


class RankRespository constructor(var strategy:String): BaseRepository(){
    private val mDataSource = RankDataSource(strategy)

    fun fetchInitialData(callBack:(MutableList<RankListBean.Item>)->Unit){
        mDataSource.loadInitial {
            callBack(it)
        }
    }

    fun fetchAfterData(callBack:(MutableList<RankListBean.Item>)->Unit){
        mDataSource.loadAfter {
            callBack(it)
        }
    }

    fun getLoadDataStatus() = mDataSource.mLoadStatus
}