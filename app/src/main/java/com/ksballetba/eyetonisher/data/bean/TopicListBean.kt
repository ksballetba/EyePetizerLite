package com.ksballetba.eyetonisher.data.bean

data class TopicListBean(
        val itemList: List<Item>,
        val count: Int, // 10
        val total: Int, // 0
        val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v3/specialTopics?start=10&num=10
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // banner2
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // Banner
                val id: Int, // 376
                val title: String,
                val description: String,
                val image: String, // http://img.kaiyanapp.com/e689c08adecf586f04ac623e8843fc8a.jpeg?imageMogr2/quality/60/format/jpg
                val actionUrl: String, // eyepetizer://webview/?title=%E3%80%8C%E9%B8%AD%E6%A2%A8%E3%80%8D%E6%98%9F%E4%BA%BA%E7%9A%84%E7%A6%8F%E9%9F%B3%EF%BC%8C+%E6%8B%92%E7%BB%9D%E8%A2%AB%E5%8E%8B%E5%8A%9B%E4%B8%BB%E5%AF%BC&url=http%3A%2F%2Fwww.eyepetizer.net%2Fvideos_article.html%3Fnid%3D376%26shareable%3Dtrue
                val adTrack: Any?, // null
                val shade: Boolean, // false
                val label: Label,
                val labelList: List<Any>,
                val header: Any?, // null
                val autoPlay: Boolean // false
        ) {
            data class Label(
                    val text: String,
                    val card: String,
                    val detail: Any? // null
            )
        }
    }
}