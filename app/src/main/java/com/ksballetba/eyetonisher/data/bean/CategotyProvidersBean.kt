package com.ksballetba.eyetonisher.data.bean

data class CategotyProvidersBean(
        val itemList: List<Item>,
        val count: Int, // 5
        val total: Int, // 0
        val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v4/categories/detail/pgcs?id=24&start=5&num=5
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
                val count: Int, // 2
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
                        val id: Int, // 137420
                        val title: String, // 时尚
                        val description: String, // 时尚
                        val library: String, // NOT_RECOMMEND
                        val tags: List<Tag>,
                        val consumption: Consumption,
                        val resourceType: String, // video
                        val slogan: Any?, // null
                        val provider: Provider,
                        val category: String, // 时尚
                        val author: Author,
                        val cover: Cover,
                        val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=137420&resourceType=video&editionType=default&source=aliyun
                        val thumbPlayUrl: Any?, // null
                        val duration: Int, // 113
                        val webUrl: WebUrl,
                        val releaseTime: Long, // 1542256133000
                        val playInfo: List<Any>,
                        val campaign: Any?, // null
                        val waterMarks: Any?, // null
                        val ad: Boolean, // false
                        val adTrack: Any?, // null
                        val type: String, // NORMAL
                        val titlePgc: String, // 时尚
                        val descriptionPgc: String, // 时尚
                        val remark: String, // 时尚
                        val ifLimitVideo: Boolean, // false
                        val searchWeight: Int, // 0
                        val idx: Int, // 0
                        val shareAdTrack: Any?, // null
                        val favoriteAdTrack: Any?, // null
                        val webAdTrack: Any?, // null
                        val date: Long, // 1542256133000
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
                            val collectionCount: Int, // 2
                            val shareCount: Int, // 0
                            val replyCount: Int // 0
                    )

                    data class Author(
                            val id: Int, // 3922
                            val icon: String, // http://img.kaiyanapp.com/81528a7a8475124ac103f08e866d33ed.png?imageMogr2/quality/60/format/jpg
                            val name: String, // Wfilm
                            val description: String, // 时尚与艺术
                            val link: String,
                            val latestReleaseTime: Long, // 1542259311000
                            val videoNum: Int, // 2
                            val adTrack: Any?, // null
                            val follow: Follow,
                            val shield: Shield,
                            val approvedNotReadyVideoCount: Int, // 0
                            val ifPgc: Boolean // true
                    ) {
                        data class Shield(
                                val itemType: String, // author
                                val itemId: Int, // 3922
                                val shielded: Boolean // false
                        )

                        data class Follow(
                                val itemType: String, // author
                                val itemId: Int, // 3922
                                val followed: Boolean // false
                        )
                    }

                    data class Provider(
                            val name: String, // PGC
                            val alias: String, // PGC
                            val icon: String
                    )

                    data class WebUrl(
                            val raw: String, // http://www.eyepetizer.net/detail.html?vid=137420
                            val forWeibo: String // http://www.eyepetizer.net/detail.html?vid=137420&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
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
                            val feed: String, // http://img.kaiyanapp.com/f235fb7306a26a0293b7754563a5584b.png?imageMogr2/quality/60/format/jpg
                            val detail: String, // http://img.kaiyanapp.com/f235fb7306a26a0293b7754563a5584b.png?imageMogr2/quality/60/format/jpg
                            val blurred: String, // http://img.kaiyanapp.com/51a683873bc10e40de53517002ad5350.jpeg?imageMogr2/quality/60/format/jpg
                            val sharing: Any?, // null
                            val homepage: Any? // null
                    )
                }
            }

            data class Header(
                    val id: Int, // 3922
                    val icon: String, // http://img.kaiyanapp.com/81528a7a8475124ac103f08e866d33ed.png?imageMogr2/quality/60/format/jpg
                    val iconType: String, // round
                    val title: String, // Wfilm
                    val subTitle: Any?, // null
                    val description: String, // 时尚与艺术
                    val actionUrl: String, // eyepetizer://pgc/detail/3922/?title=Wfilm&userType=PGC&tabIndex=1
                    val adTrack: Any?, // null
                    val follow: Follow,
                    val ifPgc: Boolean // true
            ) {
                data class Follow(
                        val itemType: String, // author
                        val itemId: Int, // 3922
                        val followed: Boolean // false
                )
            }
        }
    }
}