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
            val data: VideoInfoBean,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    )
}