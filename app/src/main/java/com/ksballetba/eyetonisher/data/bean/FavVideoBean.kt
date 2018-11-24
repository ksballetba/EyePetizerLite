package com.ksballetba.eyetonisher.data.bean

import org.litepal.crud.LitePalSupport

data class FavVideoBean(
        val videoId:Int,
        val cover:String,
        val authorAvater:String,
        val authorName:String,
        val title:String,
        val category:String,
        val duration:Int,
        val playUrl:String,
        val description:String,
        val favCount:Int,
        val commentCount:Int,
        val slogan:String
):LitePalSupport()