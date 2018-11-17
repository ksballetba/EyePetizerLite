package com.ksballetba.eyetonisher.data.bean

data class CategotyPlaylistBean(
        val itemList: List<Item>,
        val count: Int, // 5
        val total: Int, // 0
        val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v4/categories/detail/playlist?id=24&start=5&num=5
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // videoCollectionWithBrief
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // ItemCollection
                val header: Header,
                val itemList: List<Item>,
                val count: Int, // 3
                val adTrack: Any?, // null
                val footer: Any? // null
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
                        val id: Int, // 110585
                        val title: String, // 球衣巧穿搭！穿成美美哒足球宝贝
                        val description: String, // #I-SEE罐头时尚#跟世界杯抢占男人注意力，打扮成足球宝贝你绝对稳赢！3种球衣心机搭，世界杯C位出道，男人回家早
                        val library: String, // NOT_RECOMMEND
                        val tags: List<Tag>,
                        val consumption: Consumption,
                        val resourceType: String, // video
                        val slogan: Any?, // null
                        val provider: Provider,
                        val category: String, // 时尚
                        val author: Author,
                        val cover: Cover,
                        val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=110585&resourceType=video&editionType=default&source=aliyun
                        val thumbPlayUrl: Any?, // null
                        val duration: Int, // 138
                        val webUrl: WebUrl,
                        val releaseTime: Long, // 1529406992000
                        val playInfo: List<PlayInfo>,
                        val campaign: Any?, // null
                        val waterMarks: Any?, // null
                        val ad: Boolean, // false
                        val adTrack: Any?, // null
                        val type: String, // NORMAL
                        val titlePgc: String, // 球衣巧穿搭！穿成美美哒足球宝贝
                        val descriptionPgc: String, // #I-SEE罐头时尚#跟世界杯抢占男人注意力，打扮成足球宝贝你绝对稳赢！3种球衣心机搭，世界杯C位出道，男人回家早
                        val remark: String,
                        val ifLimitVideo: Boolean, // false
                        val searchWeight: Int, // 0
                        val idx: Int, // 0
                        val shareAdTrack: Any?, // null
                        val favoriteAdTrack: Any?, // null
                        val webAdTrack: Any?, // null
                        val date: Long, // 1529406992000
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
                    data class Consumption(
                            val collectionCount: Int, // 4
                            val shareCount: Int, // 8
                            val replyCount: Int // 0
                    )

                    data class WebUrl(
                            val raw: String, // http://www.eyepetizer.net/detail.html?vid=110585
                            val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=110585
                    )

                    data class Author(
                            val id: Int, // 275
                            val icon: String, // http://img.kaiyanapp.com/98f0a2b062b97645dd0ab651b63fdd4a.png
                            val name: String, // 罐头视频
                            val description: String, // 3分钟有趣有用的短视频，教你get各种生活黑科技、365天带你过最有品质的生活
                            val link: String,
                            val latestReleaseTime: Long, // 1542346211000
                            val videoNum: Int, // 950
                            val adTrack: Any?, // null
                            val follow: Follow,
                            val shield: Shield,
                            val approvedNotReadyVideoCount: Int, // 0
                            val ifPgc: Boolean // true
                    ) {
                        data class Shield(
                                val itemType: String, // author
                                val itemId: Int, // 275
                                val shielded: Boolean // false
                        )

                        data class Follow(
                                val itemType: String, // author
                                val itemId: Int, // 275
                                val followed: Boolean // false
                        )
                    }

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

                    data class Cover(
                            val feed: String, // http://img.kaiyanapp.com/fd69e86343c5efe417ba3fa1f57e65b5.png?imageMogr2/quality/60/format/jpg
                            val detail: String, // http://img.kaiyanapp.com/fd69e86343c5efe417ba3fa1f57e65b5.png?imageMogr2/quality/60/format/jpg
                            val blurred: String, // http://img.kaiyanapp.com/0994cd0d4323c743f5c8054f8d80bc71.jpeg?imageMogr2/quality/60/format/jpg
                            val sharing: Any?, // null
                            val homepage: Any? // null
                    )

                    data class PlayInfo(
                            val height: Int, // 720
                            val width: Int, // 1280
                            val urlList: List<Url>,
                            val name: String, // 高清
                            val type: String, // high
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=110585&resourceType=video&editionType=high&source=aliyun
                    ) {
                        data class Url(
                                val name: String, // ucloud
                                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=110585&resourceType=video&editionType=high&source=ucloud
                                val size: Int // 15146119
                        )
                    }
                }
            }

            data class Header(
                    val id: Int, // 365
                    val icon: String, // http://img.kaiyanapp.com/a4d9b4fa97fc289e1073cb6d88f2d7b0.png?imageMogr2/quality/60/format/jpg
                    val iconType: String, // squareWithPlayButton
                    val title: String, // i-See 罐头时尚
                    val subTitle: Any?, // null
                    val description: String, // 专辑 · 2018-10-13更新 / 60 个视频
                    val actionUrl: String, // eyepetizer://common/?title=i-See+%E7%BD%90%E5%A4%B4%E6%97%B6%E5%B0%9A&url=http%3A%2F%2Fbaobab.kaiyanapp.com%2Fapi%2Fv4%2Fplaylists%2F365%2Fvideos
                    val adTrack: Any?, // null
                    val follow: Any?, // null
                    val ifPgc: Boolean // false
            )
        }
    }
}