package com.ksballetba.eyetonisher.data.bean

data class VideoInfoBean(
        val dataType: String, // VideoBeanForClient
        val id: Int, // 135586
        val title: String, // 迪士尼短片：一波三折的惊喜计划
        val description: String, // 米奇为了给心爱的米妮送上乔迁之礼，决定叫上唐老鸭和高飞将新家粉刷成她最爱的红色。没想到过程竟然一波三折，让人捧腹大笑，最后的结果也令人意想不到。
        val library: String, // DAILY
        val tags: List<Tag>,
        val consumption: Consumption,
        val resourceType: String, // video
        val slogan: String, // 这么可爱的米老鼠已经 90 岁了
        val provider: Provider,
        val category: String, // 动画
        val author: Author,
        val cover: Cover,
        val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=135586&resourceType=video&editionType=default&source=aliyun
        val thumbPlayUrl: Any?, // null
        val duration: Int, // 226
        val webUrl: WebUrl,
        val releaseTime: Long, // 1541638800000
        val playInfo: List<PlayInfo>,
        val campaign: Any?, // null
        val waterMarks: Any?, // null
        val ad: Boolean, // false
        val adTrack: Any?, // null
        val type: String, // NORMAL
        val titlePgc: Any?, // null
        val descriptionPgc: Any?, // null
        val remark: Any?, // null
        val ifLimitVideo: Boolean, // false
        val searchWeight: Int, // 0
        val idx: Int, // 0
        val shareAdTrack: Any?, // null
        val favoriteAdTrack: Any?, // null
        val webAdTrack: Any?, // null
        val date: Long, // 1541638800000
        val promotion: Any?, // null
        val label: Any?, // null
        val labelList: List<Any>,
        val descriptionEditor: String, // 米奇为了给心爱的米妮送上乔迁之礼，决定叫上唐老鸭和高飞将新家粉刷成她最爱的红色。没想到过程竟然一波三折，让人捧腹大笑，最后的结果也令人意想不到。
        val collected: Boolean, // false
        val played: Boolean, // false
        val subtitles: List<Any>,
        val lastViewTime: Any?, // null
        val playlists: Any?, // null
        val src: Any? // null
) {
    data class Tag(
            val id: Int, // 422
            val name: String, // 迪士尼
            val actionUrl: String, // eyepetizer://tag/422/?title=%E8%BF%AA%E5%A3%AB%E5%B0%BC
            val adTrack: Any?, // null
            val desc: Any?, // null
            val bgPicture: String, // http://img.kaiyanapp.com/4091f7ddddf5d299d54b351eba463411.jpeg?imageMogr2/quality/100
            val headerImage: String, // http://img.kaiyanapp.com/4091f7ddddf5d299d54b351eba463411.jpeg?imageMogr2/quality/100
            val tagRecType: String, // NORMAL
            val childTagList: Any?, // null
            val childTagIdList: Any?, // null
            val communityIndex: Int // 0
    )

    data class Consumption(
            val collectionCount: Int, // 723
            val shareCount: Int, // 408
            val replyCount: Int // 9
    )

    data class Cover(
            val feed: String, // http://img.kaiyanapp.com/67966a5ac0569f896fe49bc2414ff01c.jpeg?imageMogr2/quality/60/format/jpg
            val detail: String, // http://img.kaiyanapp.com/67966a5ac0569f896fe49bc2414ff01c.jpeg?imageMogr2/quality/60/format/jpg
            val blurred: String, // http://img.kaiyanapp.com/07b5cfb840391730a0a12988daf7c23e.jpeg?imageMogr2/quality/60/format/jpg
            val sharing: Any?, // null
            val homepage: String // http://img.kaiyanapp.com/67966a5ac0569f896fe49bc2414ff01c.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
    )

    data class PlayInfo(
            val height: Int, // 720
            val width: Int, // 1280
            val urlList: List<Url>,
            val name: String, // 高清
            val type: String, // high
            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=135586&resourceType=video&editionType=high&source=aliyun
    ) {
        data class Url(
                val name: String, // ucloud
                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=135586&resourceType=video&editionType=high&source=ucloud
                val size: Int // 55210597
        )
    }

    data class Provider(
            val name: String, // YouTube
            val alias: String, // youtube
            val icon: String // http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
    )

    data class Author(
            val id: Int, // 364
            val icon: String, // http://img.kaiyanapp.com/5cd5076e1e2744aa940cf875b5f65211.jpeg?imageMogr2/quality/60/format/jpg
            val name: String, // 华特迪士尼影片
            val description: String, // 华特迪士尼影片（Walt Disney Pictures）是华特迪士尼公司的最重要的电影发行品牌。在此品牌下，迪士尼发行了许多优秀的动画片与真人片。
            val link: String,
            val latestReleaseTime: Long, // 1541638800000
            val videoNum: Int, // 84
            val adTrack: Any?, // null
            val follow: Follow,
            val shield: Shield,
            val approvedNotReadyVideoCount: Int, // 0
            val ifPgc: Boolean // true
    ) {
        data class Follow(
                val itemType: String, // author
                val itemId: Int, // 364
                val followed: Boolean // false
        )

        data class Shield(
                val itemType: String, // author
                val itemId: Int, // 364
                val shielded: Boolean // false
        )
    }

    data class WebUrl(
            val raw: String, // http://www.eyepetizer.net/detail.html?vid=135586
            val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=135586&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
    )
}