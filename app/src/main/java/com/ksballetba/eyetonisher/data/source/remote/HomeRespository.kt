package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.HomeListBean

class HomeRespository:BaseRepository(){

    val mDataSource = HomeDataSource()

    fun fetchInitialHomeData(callBack:(MutableList<HomeListBean.Item>)->Unit){
        mDataSource.loadInitial {
            callBack(it)
        }
    }

    fun fetchAfterHomeData(date:String,callBack:(MutableList<HomeListBean.Item>)->Unit){
        mDataSource.loadAfter(date) {
            callBack(it)
        }
    }

    fun fetchInitialRecoData(callBack:(MutableList<HomeListBean.Item>)->Unit){
        mDataSource.loadRecoInitial{
            callBack(it)
        }
    }

    fun fetchAfterRecoData(callBack:(MutableList<HomeListBean.Item>)->Unit){
        mDataSource.loadRecoAfter {
            callBack(it)
        }
    }

    fun getLoadDataStatus() = mDataSource.mLoadStatus

    fun getNextPageUrl() = mDataSource.mNextPgaeDate

}