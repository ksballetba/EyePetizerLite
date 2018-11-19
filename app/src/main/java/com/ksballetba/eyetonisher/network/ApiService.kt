package com.ksballetba.eyetonisher.network

import com.ksballetba.eyetonisher.data.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// 日报
//http://baobab.kaiyanapp.com/api/v5/index/tab/feed
// 周排行
//http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly&start=0&num=10
// 月排行
//http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly&start=0&num=10
// 总排行
//http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical&start=0&num=10
// 全部分类
//http://baobab.kaiyanapp.com/api/v4/categories/all
// 猜你喜欢
//http://baobab.kaiyanapp.com/api/v5/index/tab/rec?page=0
// 分类详情(首页)
//http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=24&udid=0e03d29034f5474f981d27cded1b2b65c8316d11
// 分类详情(全部)
//http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=24&udid=0e03d29034f5474f981d27cded1b2b65c8316d11
// 分类详情(专辑)
//http://baobab.kaiyanapp.com/api/v4/categories/detail/playlist?id=24&udid=0e03d29034f5474f981d27cded1b2b65c8316d11
// 分类详情(作者)
//http://baobab.kaiyanapp.com/api/v4/categories/detail/pgcs?id=24&udid=0e03d29034f5474f981d27cded1b2b65c8316d11
// 全景(推荐)
//http://baobab.kaiyanapp.com/api/v1/tag/videos?id=658
// 全景(广场)
//http://baobab.kaiyanapp.com/api/v6/tag/dynamics?id=658
// 专题
//http://baobab.kaiyanapp.com/api/v3/specialTopics?start=0&num=10
// 专题详情
//http://baobab.kaiyanapp.com/api/v3/lightTopics/internal/376
// 视频相关信息
//http://baobab.kaiyanapp.com/api/v2/video/135586
// 视频相关推荐
//http://baobab.kaiyanapp.com/api/v4/video/related?id=133818&vc=411&deviceModel=Nexus%206P
// 视频详情评论
//http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=135980
// 推送
//http://baobab.kaiyanapp.com/api/v3/messages?vc=411&deviceModel=Nexus%206P

interface ApiService{
    @GET("v5/index/tab/feed")
    fun getHomeList(@Query("date") date:String):Observable<HomeListBean>

    @GET("v4/rankList/videos")
    fun getRankList(@Query("strategy") strategy:String,@Query("start") start:Int,@Query("num") num:Int):Observable<RankListBean>

    @GET("v3/specialTopics")
    fun getTopicList(@Query("start") start:Int,@Query("num") num:Int):Observable<TopicListBean>

    @GET("v4/categories/all")
    fun getCateList():Observable<CateListBean>

    @GET("v5/index/tab/rec")
    fun getRecoList(@Query("page") page:Int):Observable<HomeListBean>

    @GET("v4/video/related")
    fun getRelatedList(@Query("id") id:Int,@Query("vc") vc:Int,@Query("deviceModel") deviceModel:String):Observable<RelatedVideoBean>

    @GET("v2/replies/video")
    fun getVideoReplies(@Query("videoId") videoId:Int):Observable<RepliesBean>

    @GET("v2/video/{videoId}")
    fun getVideoInfo(@Path("videoId") videoId:Int):Observable<VideoInfoBean>

    @GET("v4/categories/detail/index")
    fun getCategoryHotVideoList(@Query("id") id:Int):Observable<CategoryHomeListBean>

    @GET("v4/categories/videoList")
    fun getCategoryAllVideoList(@Query("id") id:Int,@Query("start") start:Int,@Query("num") num:Int,@Query("udid") udid:String):Observable<RankListBean>

    @GET("v4/categories/detail/playlist")
    fun getCategoryPlaylist(@Query("id") id:Int,@Query("start") start:Int,@Query("num") num:Int):Observable<CategotyPlaylistBean>

    @GET("v4/categories/detail/pgcs")
    fun getCategoryProviders(@Query("id") id:Int,@Query("start") start:Int,@Query("num") num:Int):Observable<CategotyPlaylistBean>

}