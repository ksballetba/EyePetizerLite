package com.ksballetba.eyetonisher.data.source.local

import com.ksballetba.eyetonisher.base.BaseRepository
import com.ksballetba.eyetonisher.data.bean.DownloadVideoBean
import com.ksballetba.eyetonisher.utilities.runOnIoThread

class DownloadVideoRepository(private val  downloadVideoDao: DownloadVideoDao) : BaseRepository() {

    fun getVideos() = downloadVideoDao.getVideos()

    fun insertVideo(video: DownloadVideoBean) {
        runOnIoThread {
            downloadVideoDao.insertVideo(video)
        }
    }
    fun deleteVideo(id: Int) {
        downloadVideoDao.deleteVideo(id)
    }
    fun isDownloaded(videoId:Int) = downloadVideoDao.isDownloaded(videoId)
}