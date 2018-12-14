package com.ksballetba.eyetonisher.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.ui.acitvities.FavAndDownloadActivity
import com.ksballetba.eyetonisher.utilities.DownloadUtils
import org.jetbrains.anko.toast

class DownloadService : Service() {

    lateinit var downloadUrl: String
    lateinit var downloadManager: DownloadUtils

    private val downloadListener = object : DownloadListener {
        override fun onProgress(downloadId: Int,progress: Int) {
            getNotificationManager().cancel(1)
            getNotificationManager().notify(downloadId, getNotification("下载中...", progress))
            if(progress==100){
                getNotificationManager().cancel(downloadId)
            }

        }

        override fun onSuccess() {
            stopForeground(true)
            getNotificationManager().notify(0, getNotification("下载完成", -1))
            toast("下载完成")
        }

        override fun onFailed() {
            stopForeground(true)
            getNotificationManager().notify(-1, getNotification("下载失败", -1))
            toast("下载失败")
        }
    }

    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    inner class DownloadBinder : Binder() {
        fun startDownload(url: String,fileName:String) {
            downloadUrl = url
            downloadManager = DownloadUtils.getInstance(downloadListener)
            downloadManager.downloadVideo(downloadUrl,fileName)
            startForeground(1,getNotification("视频缓存中...",0))
            toast("开始下载")
        }
    }

    private fun getNotificationManager() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun getNotification(title: String, progress: Int): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("download_id", "下载", NotificationManager.IMPORTANCE_DEFAULT)
            channel.canBypassDnd()
            channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
            channel.lightColor = Color.GREEN
            channel.canShowBadge()
            channel.setBypassDnd(true)
            channel.enableVibration(true)
            channel.shouldShowLights()
            getNotificationManager().createNotificationChannel(channel)
        }
        val intent = Intent(this, FavAndDownloadActivity::class.java)
        intent.putExtra("init_type","download")
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, "download_id")
        builder.setContentTitle(title)
        builder.setSmallIcon(R.drawable.ic_remove_red_eye_black_24dp)
        builder.setContentTitle(title)
        builder.setOnlyAlertOnce(true)
        builder.setContentIntent(pi)
        if (progress > 0) {
            builder.setContentText("$progress%")
            builder.setProgress(100, progress, false)
        }
        return builder.build()
    }


    interface DownloadListener {
        fun onProgress(downloadId:Int,progress: Int)
        fun onSuccess()
        fun onFailed()
    }


}
