package com.ksballetba.eyetonisher.data.bean

data class RepliesBean(
        val itemList: List<Item>,
        val count: Int, // 6
        val total: Int, // 5
        val nextPageUrl: Any?, // null
        val adExist: Boolean // false
) {
    data class Item(
            val type: String, // reply
            val data: Data,
            val tag: Any?, // null
            val id: Int, // 0
            val adIndex: Int // -1
    ) {
        data class Data(
                val dataType: String, // ReplyBeanForClient
                val id: Long, // 1060173190828916736
                val videoId: Int, // 135942
                val videoTitle: String, // 向英雄致敬！「复联」泪目混剪
                val parentReplyId: Long, // 0
                val rootReplyId: Long, // 1060173190828916736
                val sequence: Int, // 1
                val message: String, // 看哭了
                val replyStatus: String, // PUBLISHED
                val createTime: Long, // 1541599960000
                val user: User,
                val likeCount: Int, // 2
                val liked: Boolean, // false
                val hot: Boolean, // false
                val userType: Any?, // null
                val type: String, // video
                val actionUrl: Any?, // null
                val imageUrl: Any?, // null
                val ugcVideoId: Any?, // null
                val parentReply: Any?, // null
                val showParentReply: Boolean, // true
                val showConversationButton: Boolean, // false
                val ugcVideoUrl: Any?, // null
                val cover: Any?, // null
                val userBlocked: Boolean, // false
                val sid: String // 1060173190828916736
        ) {
            data class User(
                    val uid: Int, // 223557862
                    val nickname: String, // 兟贝公子
                    val avatar: String, // http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLeNXAWbrzUNhx1K05qkxwIajGxnXxiby9icf2VyAznqWwuJNRDibjI7zjXuVFekyNXHb59uC8BUPKZQ/132
                    val userType: String, // NORMAL
                    val ifPgc: Boolean, // false
                    val description: Any?, // null
                    val area: Any?, // null
                    val gender: Any?, // null
                    val registDate: Long, // 1469595663000
                    val releaseDate: Any?, // null
                    val cover: Any?, // null
                    val actionUrl: String, // eyepetizer://pgc/detail/223557862/?title=%E5%85%9F%E8%B4%9D%E5%85%AC%E5%AD%90&userType=NORMAL&tabIndex=0
                    val followed: Boolean, // false
                    val limitVideoOpen: Boolean, // false
                    val library: String, // BLOCK
                    val uploadStatus: String, // NORMAL
                    val bannedDate: Any?, // null
                    val bannedDays: Int // 0
            )
        }
    }
}