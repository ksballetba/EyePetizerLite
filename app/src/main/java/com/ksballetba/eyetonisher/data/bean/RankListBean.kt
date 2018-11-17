package com.ksballetba.eyetonisher.data.bean

data class RankListBean(
        val itemList: List<Item>,
        val count: Int, // 10
        val total: Int, // 0
        val nextPageUrl: Any?, // null
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
                val id: Int, // 128000
                val title: String, // 泰国避孕套广告：完美的「前戏」
                val description: String, // 这是一则来自 okamoto 安全套的广告，旨在为情侣创造更和谐的性生活。很多男生因为不懂女性心理而导致女朋友生气，片中列举了几个典型案例，并教你如何机智化解矛盾，更懂女人心。
                val library: String, // DAILY
                val tags: List<Tag>,
                val consumption: Consumption,
                val resourceType: String, // video
                val slogan: String, // 和谐性生活的必备小技巧
                val provider: Provider,
                val category: String, // 广告
                val author: Author,
                val cover: Cover,
                val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=128000&resourceType=video&editionType=default&source=aliyun
                val thumbPlayUrl: String,
                val duration: Int, // 310
                val webUrl: WebUrl,
                val releaseTime: Long, // 1538701204000
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
                val date: Long, // 1538701204000
                val promotion: Any?, // null
                val label: Any?, // null
                val labelList: List<Any>,
                val descriptionEditor: String, // 这是一则来自 okamoto 安全套的广告，旨在为情侣创造更和谐的性生活。很多男生因为不懂女性心理而导致女朋友生气，片中列举了几个典型案例，并教你如何机智化解矛盾，更懂女人心。
                val collected: Boolean, // false
                val played: Boolean, // false
                val subtitles: List<Any>,
                val lastViewTime: Any?, // null
                val playlists: Any?, // null
                val src: Any? // null
        ) {
            data class WebUrl(
                    val raw: String, // http://www.eyepetizer.net/detail.html?vid=128000
                    val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=128000
            )

            data class Author(
                    val id: Int, // 2813
                    val icon: String, // http://img.kaiyanapp.com/f9f8fe18d96ed81cd6ee4ea19c66fd0e.png?imageMogr2/quality/60/format/jpg
                    val name: String, // 曼谷直击
                    val description: String, // 泰国超有脑洞广告分享，喜欢就关注我们吧~
                    val link: String,
                    val latestReleaseTime: Long, // 1538701204000
                    val videoNum: Int, // 5
                    val adTrack: Any?, // null
                    val follow: Follow,
                    val shield: Shield,
                    val approvedNotReadyVideoCount: Int, // 0
                    val ifPgc: Boolean // true
            ) {
                data class Follow(
                        val itemType: String, // author
                        val itemId: Int, // 2813
                        val followed: Boolean // false
                )

                data class Shield(
                        val itemType: String, // author
                        val itemId: Int, // 2813
                        val shielded: Boolean // false
                )
            }

            data class PlayInfo(
                    val height: Int, // 720
                    val width: Int, // 1280
                    val urlList: List<Url>,
                    val name: String, // 高清
                    val type: String, // high
                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=128000&resourceType=video&editionType=high&source=aliyun
            ) {
                data class Url(
                        val name: String, // ucloud
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=128000&resourceType=video&editionType=high&source=ucloud
                        val size: Int // 19390037
                )
            }

            data class Cover(
                    val feed: String, // http://img.kaiyanapp.com/55672992133fb8efaed36ca0bde322f9.jpeg?imageMogr2/quality/60/format/jpg
                    val detail: String, // http://img.kaiyanapp.com/55672992133fb8efaed36ca0bde322f9.jpeg?imageMogr2/quality/60/format/jpg
                    val blurred: String, // http://img.kaiyanapp.com/afa9886f7bd8904e6bd1b2c5ff225760.jpeg?imageMogr2/quality/60/format/jpg
                    val sharing: Any?, // null
                    val homepage: String // http://img.kaiyanapp.com/55672992133fb8efaed36ca0bde322f9.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
            )

            data class Consumption(
                    val collectionCount: Int, // 2227
                    val shareCount: Int, // 1847
                    val replyCount: Int // 40
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
                    val name: String, // 定制来源
                    val alias: String, // CustomSrc
                    val icon: String
            )
        }
    }
}