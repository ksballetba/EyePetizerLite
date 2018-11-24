package com.ksballetba.eyetonisher.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import org.litepal.LitePal
import org.litepal.extension.delete
import org.litepal.extension.find
import org.litepal.extension.findAll

class FavVideoDao{

    private val liveVideos = MutableLiveData<List<FavVideoBean>>()

    fun getVideos():LiveData<List<FavVideoBean>>{
        val videos = LitePal.findAll<FavVideoBean>()
        liveVideos.postValue(videos)
        return liveVideos
    }

    fun insertVideo(video:FavVideoBean){
        if(LitePal.where("videoId = ?",video.videoId.toString()).find<FavVideoBean>().isEmpty()){
            video.save()
        }
    }

    fun deleteVideo(id:Int){
        LitePal.deleteAll(FavVideoBean::class.java,"videoId = ?", id.toString())
    }
}