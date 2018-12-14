package com.ksballetba.eyetonisher.data.bean

import org.litepal.crud.LitePalSupport

data class DownloadVideoBean(
        var videoId:Int?,
        var cover:String?,
        var authorAvater:String?,
        var authorName:String?,
        var title:String?,
        var category:String?,
        var duration:Int?,
        var playUrl:String?,
        var description:String?,
        var favCount:Int?,
        var commentCount:Int?,
        var slogan:String?
): LitePalSupport()