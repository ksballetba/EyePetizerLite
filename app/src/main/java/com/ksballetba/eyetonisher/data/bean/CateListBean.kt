package com.ksballetba.eyetonisher.data.bean

data class CateListBean(
        val itemList: List<Item>,
        val count: Int, // 20
        val total: Int, // 0
        val nextPageUrl: Any?, // null
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // squareCard
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // SquareCard
                val id: Int, // 38
                val title: String, // #综艺
                val image: String, // http://img.kaiyanapp.com/a17745312139694dc1f0c40984533328.png?imageMogr2/quality/60/format/jpg
                val actionUrl: String, // eyepetizer://category/38/?title=%E7%BB%BC%E8%89%BA
                val shade: Boolean, // true
                val adTrack: Any? // null
        )
    }
}