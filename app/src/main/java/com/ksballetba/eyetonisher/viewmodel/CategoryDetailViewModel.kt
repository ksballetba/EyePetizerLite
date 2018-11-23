package com.ksballetba.eyetonisher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.CategoryHomeListBean
import com.ksballetba.eyetonisher.data.bean.CategotyPlaylistBean
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.data.source.remote.CategoryDetailRespository

class CategoryDetailViewModel constructor(private val categoryDetailRespository: CategoryDetailRespository): BaseViewModel(categoryDetailRespository){
    fun fetchHotVideoList(): LiveData<MutableList<CategoryHomeListBean.Item>> {
        val result = MutableLiveData<MutableList<CategoryHomeListBean.Item>>()
        categoryDetailRespository.fetchHotVideoList {
            result.postValue(it)
        }
        return result
    }

    fun fetchAllVideoListInitial(): LiveData<MutableList<RankListBean.Item>> {
        val result = MutableLiveData<MutableList<RankListBean.Item>>()
        categoryDetailRespository.fetchAllVideoListInitial {
            result.postValue(it)
        }
        return result
    }

    fun fetchAllVideoListAfter(): LiveData<MutableList<RankListBean.Item>> {
        val result = MutableLiveData<MutableList<RankListBean.Item>>()
        categoryDetailRespository.fetchAllVideoListAfter {
            result.postValue(it)
        }
        return result
    }

    fun fetchPlaylistInitial(): LiveData<MutableList<CategotyPlaylistBean.Item>> {
        val result = MutableLiveData<MutableList<CategotyPlaylistBean.Item>>()
        categoryDetailRespository.fetchPlaylistInitial {
            result.postValue(it)
        }
        return result
    }

    fun fetchPlaylistAfter(): LiveData<MutableList<CategotyPlaylistBean.Item>> {
        val result = MutableLiveData<MutableList<CategotyPlaylistBean.Item>>()
        categoryDetailRespository.fetchPlaylistAfter {
            result.postValue(it)
        }
        return result
    }

    fun fetchProviderListInitial(): LiveData<MutableList<CategotyPlaylistBean.Item>> {
        val result = MutableLiveData<MutableList<CategotyPlaylistBean.Item>>()
        categoryDetailRespository.fetchProviderListInitial {
            result.postValue(it)
        }
        return result
    }

    fun fetchProviderListAfter(): LiveData<MutableList<CategotyPlaylistBean.Item>> {
        val result = MutableLiveData<MutableList<CategotyPlaylistBean.Item>>()
        categoryDetailRespository.fetchProviderListAfter {
            result.postValue(it)
        }
        return result
    }

    fun fetchLoadDataStatus() = categoryDetailRespository.getLoadDataStatus()
}