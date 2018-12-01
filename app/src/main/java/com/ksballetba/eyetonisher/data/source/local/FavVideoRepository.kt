package com.ksballetba.eyetonisher.data.source.local

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.utilities.runOnIoThread

class FavVideoRepository(private val favVideoDao: FavVideoDao) : BaseRepository() {

    fun getVideos() = favVideoDao.getVideos()

    fun insertVideo(video: FavVideoBean) {
        runOnIoThread {
            favVideoDao.insertVideo(video)
        }
    }
    fun deleteVideo(id: Int) {
        favVideoDao.deleteVideo(id)
    }
}