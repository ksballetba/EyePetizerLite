package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.CateListBean
import com.ksballetba.eyetonisher.data.source.remote.CateRespository

class CateViewModel constructor(private var cateRespository: CateRespository): BaseViewModel(cateRespository){
    fun fetchData(): LiveData<MutableList<CateListBean.Item>> {
        val result = MutableLiveData<MutableList<CateListBean.Item>>()
        cateRespository.fetchInitialData {
            result.postValue(it)
        }
        return result
    }
}