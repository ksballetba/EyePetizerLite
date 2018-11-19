package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.CategoryHomeListBean
import com.ksballetba.eyetonisher.data.bean.CategotyPlaylistBean
import com.ksballetba.eyetonisher.data.bean.CategotyProvidersBean
import com.ksballetba.eyetonisher.data.bean.RankListBean

class CategoryDetailRespository constructor(var id:Int): BaseRepository(){
    private val mDataSource = CategoryDetailDataSource(id)

    fun fetchHotVideoList(callBack:(MutableList<CategoryHomeListBean.Item>)->Unit){
        mDataSource.loadHotVideoList {
            callBack(it)
        }
    }

    fun fetchAllVideoListInitial(callBack:(MutableList<RankListBean.Item>)->Unit){
        mDataSource.loadAllVideoListInitial {
            callBack(it)
        }
    }

    fun fetchAllVideoListAfter(callBack:(MutableList<RankListBean.Item>)->Unit){
        mDataSource.loadAllVideoListAfter {
            callBack(it)
        }
    }

    fun fetchPlaylistInitial(callBack:(MutableList<CategotyPlaylistBean.Item>)->Unit){
        mDataSource.loadPlaylistInitial {
            callBack(it)
        }
    }

    fun fetchPlaylistAfter(callBack:(MutableList<CategotyPlaylistBean.Item>)->Unit){
        mDataSource.loadPlaylistAfter {
            callBack(it)
        }
    }

    fun fetchProviderListInitial(callBack:(MutableList<CategotyPlaylistBean.Item>)->Unit){
        mDataSource.loadProviderListInitial {
            callBack(it)
        }
    }

    fun fetchProviderListAfter(callBack:(MutableList<CategotyPlaylistBean.Item>)->Unit){
        mDataSource.loadProviderListAfter {
            callBack(it)
        }
    }

    fun getLoadDataStatus() = mDataSource.mLoadStatus
}