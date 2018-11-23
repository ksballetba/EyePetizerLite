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
            val data: VideoInfoBean,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    )
}