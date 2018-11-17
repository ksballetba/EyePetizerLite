package com.ksballetba.eyetonisher.data.bean

data class HomeListBean(
        val itemList: List<Item>,
        val count: Int, // 17
        val total: Int, // 0
        val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v5/index/tab/feed?date=1540774800000&num=2
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // followCard
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            var isFirst:Boolean? = false,
            var title:String? = "无",
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // FollowCard
                val header: Header,
                val text:String?,
                val content: Content,
                val adTrack: Any? // null
        ) {
            data class Header(
                    val id: Int, // 133257
                    val title: String, // 开眼创意精选
                    val font: Any?, // null
                    val subTitle: Any?, // null
                    val subTitleFont: Any?, // null
                    val textAlign: String, // left
                    val cover: Any?, // null
                    val label: Any?, // null
                    val actionUrl: String, // eyepetizer://pgc/detail/2161/?title=%E5%BC%80%E7%9C%BC%E5%88%9B%E6%84%8F%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                    val labelList: Any?, // null
                    val rightText: Any?, // null
                    val icon: String, // http://img.kaiyanapp.com/f4a9aba1c6857ee0cefcdc5aee0a1fc9.png?imageMogr2/quality/60/format/jpg
                    val iconType: String, // round
                    val description: String, // #创意 / 收录于 每日编辑精选
                    val time: Long, // 1540861204000
                    val showHateVideo: Boolean // false
            )

            data class Content(
                    val type: String, // video
                    val data: Data,
                    val tag: Any?, // null
                    val id: Int, // 0
                    val adIndex: Int // -1
            ) {
                data class Data(
                        val dataType: String, // VideoBeanForClient
                        val id: Int, // 133257
                        val title: String, // 色彩混搭之美：生命诞生的多样性
                        val description: String, // 短片作者用 3D 和酒精墨水结合的技术表达生命的诞生。这位来自瑞典的混血儿在创作中不断挣扎于自己的身份认同，他想通过此片表达「我不属于一个类别，我来自世界各地」。 From Ariel Lu
                        val library: String, // DAILY
                        val tags: List<Tag>,
                        val consumption: Consumption,
                        val resourceType: String, // video
                        val slogan: String, // 你不属于任何标签，只属于这个世界
                        val provider: Provider,
                        val category: String, // 创意
                        val author: Author,
                        val cover: Cover,
                        val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133257&resourceType=video&editionType=default&source=aliyun
                        val thumbPlayUrl: Any?, // null
                        val duration: Int, // 52
                        val webUrl: WebUrl,
                        val releaseTime: Long, // 1540861204000
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
                        val date: Long, // 1540861200000
                        val promotion: Any?, // null
                        val label: Any?, // null
                        val labelList: List<Any>,
                        val descriptionEditor: String, // 短片作者用 3D 和酒精墨水结合的技术表达生命的诞生。这位来自瑞典的混血儿在创作中不断挣扎于自己的身份认同，他想通过此片表达「我不属于一个类别，我来自世界各地」。 From Ariel Lu
                        val collected: Boolean, // false
                        val played: Boolean, // false
                        val subtitles: List<Any>,
                        val lastViewTime: Any?, // null
                        val playlists: Any?, // null
                        val src: Any? // null
                ) {
                    data class Tag(
                            val id: Int, // 2
                            val name: String, // 创意
                            val actionUrl: String, // eyepetizer://tag/2/?title=%E5%88%9B%E6%84%8F
                            val adTrack: Any?, // null
                            val desc: String,
                            val bgPicture: String, // http://img.kaiyanapp.com/1b457058cf2b317304ff9f70543c040d.png?imageMogr2/quality/60/format/jpg
                            val headerImage: String, // http://img.kaiyanapp.com/fdefdb34cbe3d2ac9964d306febe9025.jpeg?imageMogr2/quality/100
                            val tagRecType: String, // NORMAL
                            val childTagList: Any?, // null
                            val childTagIdList: Any?, // null
                            val communityIndex: Int // 0
                    )

                    data class Provider(
                            val name: String, // Vimeo
                            val alias: String, // vimeo
                            val icon: String // http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png
                    )

                    data class Cover(
                            val feed: String, // http://img.kaiyanapp.com/b443578c3dbae7911885d0c6880c905f.jpeg?imageMogr2/quality/60/format/jpg
                            val detail: String, // http://img.kaiyanapp.com/b443578c3dbae7911885d0c6880c905f.jpeg?imageMogr2/quality/60/format/jpg
                            val blurred: String, // http://img.kaiyanapp.com/b478f20a3dda8b913b7f69d2c7005e42.jpeg?imageMogr2/quality/60/format/jpg
                            val sharing: Any?, // null
                            val homepage: String // http://img.kaiyanapp.com/b443578c3dbae7911885d0c6880c905f.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                    )

                    data class WebUrl(
                            val raw: String, // http://www.eyepetizer.net/detail.html?vid=133257
                            val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=133257&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                    )

                    data class Consumption(
                            val collectionCount: Int, // 259
                            val shareCount: Int, // 57
                            val replyCount: Int // 4
                    )

                    data class Author(
                            val id: Int, // 2161
                            val icon: String, // http://img.kaiyanapp.com/f4a9aba1c6857ee0cefcdc5aee0a1fc9.png?imageMogr2/quality/60/format/jpg
                            val name: String, // 开眼创意精选
                            val description: String, // 技术与审美结合，探索视觉的无限可能
                            val link: String,
                            val latestReleaseTime: Long, // 1540861204000
                            val videoNum: Int, // 580
                            val adTrack: Any?, // null
                            val follow: Follow,
                            val shield: Shield,
                            val approvedNotReadyVideoCount: Int, // 0
                            val ifPgc: Boolean // true
                    ) {
                        data class Shield(
                                val itemType: String, // author
                                val itemId: Int, // 2161
                                val shielded: Boolean // false
                        )

                        data class Follow(
                                val itemType: String, // author
                                val itemId: Int, // 2161
                                val followed: Boolean // false
                        )
                    }

                    data class PlayInfo(
                            val height: Int, // 720
                            val width: Int, // 1280
                            val urlList: List<Url>,
                            val name: String, // 高清
                            val type: String, // high
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133257&resourceType=video&editionType=high&source=aliyun
                    ) {
                        data class Url(
                                val name: String, // ucloud
                                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133257&resourceType=video&editionType=high&source=ucloud
                                val size: Int // 6364997
                        )
                    }
                }
            }
        }
    }
}