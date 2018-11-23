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
                    val data: VideoInfoBean,
                    val tag: Any?, // null
                    val id: Int, // 0
                    val adIndex: Int // -1
            )

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