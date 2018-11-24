package com.ksballetba.eyetonisher.data.bean

data class TopicInfoBean(
        val id: Int, // 390
        val headerImage: String, // http://img.kaiyanapp.com/600a1f0dbaf354b853dbfc64b2b32e65.jpeg?imageMogr2/quality/60/format/jpg
        val brief: String, // 每周欧美新歌推荐，帮你的耳朵种草~
        val text: String, // 最近两周的欧美乐坛专辑要比单曲精彩的多！在经历了大上周加拿大电鳗 Kris Who 在 iTunes 扰榜事件之后，上周国外的粉丝为了庆祝天后 Mariah Carey 发布新专辑，将她与 17 年前发行的原声「Glitter」冲上多国 iTunes 专辑榜冠军，并在社交媒体上刷起了 #Justice4Glitter 的话题。而牛姐的新专辑「Caution」可谓生不逢时，前有「Glitter」后有养老保险「All I Wanna For Christmas Is You」。另一张让人期待的专辑就是电影「马戏之王」原声的重制专辑「The Greatest Showman: Reimagined」，其中邀请了包括 P!nk、Kelly Clackson 和 Kesha 等大家耳熟能详的歌手，也有 Years & Years、Anne-Marie 等这两年炙手可热的新人，将电影原声重新编曲翻唱。此外，英国女团 Little Mix 也在上周发行了专辑「LM5」，从整张专辑打单发行的歌曲来看，这张专辑有着慢慢的女权和平权的味道，她们联手 Nicki Minaj 的主打单曲「Woman Like Me」也在几周前与大家见过面了。
        val shareLink: String, // http://www.eyepetizer.net/videos_article.html?nid=390&shareable=true
        val itemList: List<Item>,
        val count: Int, // 17
        val adTrack: Any? // null
) {
    data class Item(
            val type: String, // autoPlayFollowCard
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 138134
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // FollowCard
                val header: Header,
                val content: Content,
                val adTrack: Any? // null
        ) {
            data class Header(
                    val id: Int, // 138134
                    val actionUrl: String, // eyepetizer://pgc/detail/817/?title=Music%20Video%20%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                    val labelList: Any?, // null
                    val icon: String, // http://img.kaiyanapp.com/b0c49e7fd388795f87d1c0c80e666882.jpeg?imageMogr2/quality/60/format/jpg
                    val iconType: String, // round
                    val time: Long, // 1542610114000
                    val showHateVideo: Boolean, // false
                    val followType: String, // author
                    val tagId: Int, // 0
                    val tagName: Any?, // null
                    val issuerName: String, // Music Video 精选
                    val topShow: Boolean // false
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