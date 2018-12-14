package com.ksballetba.eyetonisher.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ksballetba.eyetonisher.data.bean.DownloadVideoBean
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import org.litepal.LitePal
import org.litepal.extension.find
import org.litepal.extension.findAll

class DownloadVideoDao{

    private val liveVideos = MutableLiveData<List<DownloadVideoBean>>()
    private val isDownloaded = MutableLiveData<Boolean>()

    fun getVideos(): LiveData<List<DownloadVideoBean>> {
        val videos = LitePal.findAll<DownloadVideoBean>()
        liveVideos.postValue(videos)
        return liveVideos
    }

    fun insertVideo(video: DownloadVideoBean){
        if(LitePal.where("videoId = ?",video.videoId.toString()).find<DownloadVideoBean>().isEmpty()){
            video.save()
        }
    }

    fun deleteVideo(id:Int){
        LitePal.deleteAll(DownloadVideoBean::class.java,"videoId = ?", id.toString())
    }

    fun isDownloaded(videoId:Int):LiveData<Boolean>{
        if(LitePal.where("videoId = ?",videoId.toString()).find<DownloadVideoBean>().isEmpty()){
            isDownloaded.postValue(false)
        }else{
            isDownloaded.postValue(true)
        }
        return isDownloaded
    }
}