package com.ksballetba.eyetonisher.data.bean

data class CategoryHomeListBean(
        val itemList: List<Item>,
        val count: Int, // 18
        val total: Int, // 0
        val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=24&page=1&needFilter=true
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // video
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // VideoBeanForClient
                val id: Int, // 133988
                val title: String, // 什么是中国式精致？从自然中来更高于自然！
                val description: String, // 中国设计师品牌WANG FENG从⾃然之“物”中汲取灵感，打造2019春夏⾼级定制系列，通过精湛⼯艺与美学设计呈递曼妙绮丽的浪漫意境。
                val library: String, // DEFAULT
                val tags: List<Tag>,
                val consumption: Consumption,
                val resourceType: String, // video
                val slogan: Any?, // null
                val provider: Provider,
                val category: String, // 时尚
                val author: Author,
                val cover: Cover,
                val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133988&resourceType=video&editionType=default&source=aliyun
                val thumbPlayUrl: Any?, // null
                val duration: Int, // 83
                val webUrl: WebUrl,
                val releaseTime: Long, // 1540441653000
                val playInfo: List<PlayInfo>,
                val campaign: Any?, // null
                val waterMarks: Any?, // null
                val ad: Boolean, // false
                val adTrack: Any?, // null
                val type: String, // NORMAL
                val titlePgc: String, // 什么是中国式精致？从自然中来更高于自然！
                val descriptionPgc: String, // 中国设计师品牌WANG FENG从⾃然之“物”中汲取灵感，打造2019春夏⾼级定制系列，通过精湛⼯艺与美学设计呈递曼妙绮丽的浪漫意境。
                val remark: String,
                val ifLimitVideo: Boolean, // false
                val searchWeight: Int, // 0
                val idx: Int, // 0
                val shareAdTrack: Any?, // null
                val favoriteAdTrack: Any?, // null
                val webAdTrack: Any?, // null
                val date: Long, // 1540441653000
                val promotion: Any?, // null
                val label: Any?, // null
                val labelList: List<Any>,
                val descriptionEditor: String,
                val collected: Boolean, // false
                val played: Boolean, // false
                val subtitles: List<Any>,
                val lastViewTime: Any?, // null
                val playlists: Any?, // null
                val src: Any? // null
        ) {
            data class Cover(
                    val feed: String, // http://img.kaiyanapp.com/314eae5d237c8a6ce8c634112e836881.png?imageMogr2/quality/60/format/jpg
                    val detail: String, // http://img.kaiyanapp.com/314eae5d237c8a6ce8c634112e836881.png?imageMogr2/quality/60/format/jpg
                    val blurred: String, // http://img.kaiyanapp.com/2966b8c62bb2e1b82d5d2e9deb250964.jpeg?imageMogr2/quality/60/format/jpg
                    val sharing: Any?, // null
                    val homepage: Any? // null
            )

            data class WebUrl(
                    val raw: String, // http://www.eyepetizer.net/detail.html?vid=133988
                    val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=133988&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
            )

            data class Consumption(
                    val collectionCount: Int, // 53
                    val shareCount: Int, // 39
                    val replyCount: Int // 1
            )

            data class Provider(
                    val name: String, // PGC
                    val alias: String, // PGC
                    val icon: String
            )

            data class Tag(
                    val id: Int, // 26
                    val name: String, // 时尚
                    val actionUrl: String, // eyepetizer://tag/26/?title=%E6%97%B6%E5%B0%9A
                    val adTrack: Any?, // null
                    val desc: Any?, // null
                    val bgPicture: String, // http://img.kaiyanapp.com/34b720d05d98c905432e9906d5b9cdf7.jpeg?imageMogr2/quality/100
                    val headerImage: String, // http://img.kaiyanapp.com/34b720d05d98c905432e9906d5b9cdf7.jpeg?imageMogr2/quality/100
                    val tagRecType: String, // NORMAL
                    val childTagList: Any?, // null
                    val childTagIdList: Any?, // null
                    val communityIndex: Int // 0
            )

            data class Author(
                    val id: Int, // 418
                    val icon: String, // http://img.kaiyanapp.com/0b27dac47841e5a84c172edd7aceb5a6.jpeg?imageMogr2/quality/60/format/jpg
                    val name: String, // Peter XU Studio
                    val description: String, // 时尚达人徐峰立老师的团队，骨灰级国内外秀场报道，直击四大时装周最前沿。
                    val link: String,
                    val latestReleaseTime: Long, // 1542431082000
                    val videoNum: Int, // 168
                    val adTrack: Any?, // null
                    val follow: Follow,
                    val shield: Shield,
                    val approvedNotReadyVideoCount: Int, // 0
                    val ifPgc: Boolean // true
            ) {
                data class Shield(
                        val itemType: String, // author
                        val itemId: Int, // 418
                        val shielded: Boolean // false
                )

                data class Follow(
                        val itemType: String, // author
                        val itemId: Int, // 418
                        val followed: Boolean // false
                )
            }

            data class PlayInfo(
                    val height: Int, // 720
                    val width: Int, // 1280
                    val urlList: List<Url>,
                    val name: String, // 高清
                    val type: String, // high
                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133988&resourceType=video&editionType=high&source=aliyun
            ) {
                data class Url(
                        val name: String, // ucloud
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=133988&resourceType=video&editionType=high&source=ucloud
                        val size: Int // 22964750
                )
            }
        }
    }
}