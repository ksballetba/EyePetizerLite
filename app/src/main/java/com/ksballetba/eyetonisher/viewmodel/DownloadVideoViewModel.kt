package com.ksballetba.eyetonisher.viewmodel

import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.DownloadVideoBean
import com.ksballetba.eyetonisher.data.source.local.DownloadVideoRepository

class DownloadVideoViewModel(private val downloadVideoRepository: DownloadVideoRepository): BaseViewModel(downloadVideoRepository){

    fun getVideos() = downloadVideoRepository.getVideos()

    fun insertVideo(video: DownloadVideoBean){
        downloadVideoRepository.insertVideo(video)
    }

    fun deleteVideo(id:Int){
        downloadVideoRepository.deleteVideo(id)
    }

    fun isDownloaded(videoId:Int) = downloadVideoRepository.isDownloaded(videoId)
}