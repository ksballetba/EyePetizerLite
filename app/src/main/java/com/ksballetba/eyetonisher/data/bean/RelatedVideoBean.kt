package com.ksballetba.eyetonisher.data.bean

data class RelatedVideoBean(
        val itemList: List<Item>,
        val count: Int, // 12
        val total: Int, // 0
        val nextPageUrl: Any?, // null
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // videoSmallCard
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // VideoBeanForClient
                val id: Int, // 92640
                val title: String, // 请你一定一定一定不要忘了我！
                val description: String, // 日清食品这次和汽车品牌 NISSAN 合作拍摄了这个又萌又深刻的广告：将总是躲在汽车各处取暖的猫咪比成是泡面里「默默」被遗忘的卷心菜，但只需轻轻拍打盖子，美味就不会溜走！同样，也能唤醒在车内的喵星人。这次，请你别忘了我，因为我还想当你一辈子的小可爱。From moviecollectionjp
                val library: String, // DAILY
                val tags: List<Tag>,
                val consumption: Consumption,
                val resourceType: String, // video
                val slogan: String, // 我还想一辈子当你的小可爱
                val provider: Provider,
                val category: String, // 广告
                val author: Author,
                val cover: Cover,
                val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=92640&resourceType=video&editionType=default&source=aliyun
                val thumbPlayUrl: String,
                val duration: Int, // 106
                val webUrl: WebUrl,
                val releaseTime: Long, // 1522198802000
                val playInfo: List<PlayInfo>,
                val campaign: Any?, // null
                val waterMarks: Any?, // null
                val ad: Boolean, // false
                val adTrack: Any?, // null
                val type: String, // NORMAL
                val titlePgc: String,
                val descriptionPgc: String,
                val remark: String,
                val ifLimitVideo: Boolean, // false
                val searchWeight: Int, // 0
                val idx: Int, // 0
                val shareAdTrack: Any?, // null
                val favoriteAdTrack: Any?, // null
                val webAdTrack: Any?, // null
                val date: Long, // 1522198802000
                val promotion: Any?, // null
                val label: Any?, // null
                val labelList: List<Any>,
                val descriptionEditor: String, // 日清食品这次和汽车品牌 NISSAN 合作拍摄了这个又萌又深刻的广告：将总是躲在汽车各处取暖的猫咪比成是泡面里「默默」被遗忘的卷心菜，但只需轻轻拍打盖子，美味就不会溜走！同样，也能唤醒在车内的喵星人。这次，请你别忘了我，因为我还想当你一辈子的小可爱。From moviecollectionjp
                val collected: Boolean, // false
                val played: Boolean, // false
                val subtitles: List<Any>,
                val lastViewTime: Any?, // null
                val playlists: Any?, // null
                val src: Int // 11
        ) {
            data class PlayInfo(
                    val height: Int, // 720
                    val width: Int, // 1280
                    val urlList: List<Url>,
                    val name: String, // 高清
                    val type: String, // high
                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=92640&resourceType=video&editionType=high&source=aliyun
            ) {
                data class Url(
                        val name: String, // ucloud
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=92640&resourceType=video&editionType=high&source=ucloud
                        val size: Int // 16809612
                )
            }

            data class Consumption(
                    val collectionCount: Int, // 3594
                    val shareCount: Int, // 3613
                    val replyCount: Int // 47
            )

            data class Cover(
                    val feed: String, // http://img.kaiyanapp.com/9138a26ed56cfec3eb6b1aff638ae059.png?imageMogr2/quality/60/format/jpg
                    val detail: String, // http://img.kaiyanapp.com/9138a26ed56cfec3eb6b1aff638ae059.png?imageMogr2/quality/60/format/jpg
                    val blurred: String, // http://img.kaiyanapp.com/02b35937be55c50778ec6ba72fce2e65.png?imageMogr2/quality/60/format/jpg
                    val sharing: Any?, // null
                    val homepage: String // http://img.kaiyanapp.com/9138a26ed56cfec3eb6b1aff638ae059.png?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
            )

            data class WebUrl(
                    val raw: String, // http://www.eyepetizer.net/detail.html?vid=92640
                    val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=92640
            )

            data class Tag(
                    val id: Int, // 16
                    val name: String, // 广告
                    val actionUrl: String, // eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A
                    val adTrack: Any?, // null
                    val desc: Any?, // null
                    val bgPicture: String, // http://img.kaiyanapp.com/e41e74fe73882b552de00d95d56748d2.jpeg?imageMogr2/quality/60
                    val headerImage: String, // http://img.kaiyanapp.com/3054658dbd559ac42c4c282e9cab7a32.jpeg?imageMogr2/quality/100
                    val tagRecType: String, // NORMAL
                    val childTagList: Any?, // null
                    val childTagIdList: Any?, // null
                    val communityIndex: Int // 0
            )

            data class Provider(
                    val name: String, // YouTube
                    val alias: String, // youtube
                    val icon: String // http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
            )

            data class Author(
                    val id: Int, // 2162
                    val icon: String, // http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                    val name: String, // 开眼广告精选
                    val description: String, // 为广告人的精彩创意点赞
                    val link: String,
                    val latestReleaseTime: Long, // 1541898000000
                    val videoNum: Int, // 1033
                    val adTrack: Any?, // null
                    val follow: Follow,
                    val shield: Shield,
                    val approvedNotReadyVideoCount: Int, // 0
                    val ifPgc: Boolean // true
            ) {
                data class Shield(
                        val itemType: String, // author
                        val itemId: Int, // 2162
                        val shielded: Boolean // false
                )

                data class Follow(
                        val itemType: String, // author
                        val itemId: Int, // 2162
                        val followed: Boolean // false
                )
            }
        }
    }
}