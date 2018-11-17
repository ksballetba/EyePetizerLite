package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.CateListBean

class CateRespository: BaseRepository(){

    val mDataSource = CateDataSource()

    fun fetchInitialData(callBack:(MutableList<CateListBean.Item>)->Unit){
        mDataSource.loadInitial {
            callBack(it)
        }
    }
}