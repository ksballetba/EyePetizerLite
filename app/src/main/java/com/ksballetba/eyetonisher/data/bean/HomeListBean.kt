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
                    val data: VideoInfoBean,
                    val tag: Any?, // null
                    val id: Int, // 0
                    val adIndex: Int // -1
            )

        }
    }
}