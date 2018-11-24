package com.ksballetba.eyetonisher.viewmodel

import com.ksballetba.eyetonisher.base.BaseViewModel
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.data.source.local.FavVideoRepository

class FavVideoViewModel(private val favVideoRepository: FavVideoRepository):BaseViewModel(favVideoRepository){

    fun getVideos() = favVideoRepository.getVideos()

    fun insertVideo(video: FavVideoBean){
        favVideoRepository.insertVideo(video)
    }

    fun deleteVideo(id:Int){
        favVideoRepository.deleteVideo(id)
    }
}