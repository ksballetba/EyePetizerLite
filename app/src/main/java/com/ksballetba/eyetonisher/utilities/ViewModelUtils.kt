package com.ksballetba.eyetonisher.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ksballetba.eyetonisher.data.source.local.DownloadVideoDao
import com.ksballetba.eyetonisher.data.source.local.DownloadVideoRepository
import com.ksballetba.eyetonisher.data.source.local.FavVideoDao
import com.ksballetba.eyetonisher.data.source.local.FavVideoRepository
import com.ksballetba.eyetonisher.data.source.remote.*
import com.ksballetba.eyetonisher.viewmodel.*

fun getRankViewModel(fragment: Fragment,strategy:String): RankViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = RankRespository(strategy)
            @Suppress("UNCHECKED_CAST")
            return RankViewModel(respository) as T
        }
    })[RankViewModel::class.java]
}

fun getHomeViewModel(fragment: Fragment): HomeViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = HomeRespository()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(respository) as T
        }
    })[HomeViewModel::class.java]
}


fun getTopicViewModel(fragment: Fragment): TopicViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = TopicRespository()
            @Suppress("UNCHECKED_CAST")
            return TopicViewModel(respository) as T
        }
    })[TopicViewModel::class.java]
}

fun getCateViewModel(fragment: Fragment): CateViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = CateRespository()
            @Suppress("UNCHECKED_CAST")
            return CateViewModel(respository) as T
        }
    })[CateViewModel::class.java]
}


fun getVideoDetailViewModel(activity: FragmentActivity,videoId:Int): VideoDetailViewModel {
    return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = VideoDetailRespository(videoId)
            @Suppress("UNCHECKED_CAST")
            return VideoDetailViewModel(respository) as T
        }
    })[VideoDetailViewModel::class.java]
}


fun getCategoryDeatilViewModel(fragment: Fragment,id:Int): CategoryDetailViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = CategoryDetailRespository(id)
            @Suppress("UNCHECKED_CAST")
            return CategoryDetailViewModel(respository) as T
        }
    })[CategoryDetailViewModel::class.java]
}

fun getTopicDetailViewModel(activity: FragmentActivity,id:Int): TopicDetailViewModel {
    return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = TopicDetailRespository(id)
            @Suppress("UNCHECKED_CAST")
            return TopicDetailViewModel(respository) as T
        }
    })[TopicDetailViewModel::class.java]
}

fun getFavVideoViewModel(activity: FragmentActivity): FavVideoViewModel {
    return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = FavVideoRepository(FavVideoDao())
            @Suppress("UNCHECKED_CAST")
            return FavVideoViewModel(respository) as T
        }
    })[FavVideoViewModel::class.java]
}

fun getFavVideoViewModel(fragment: Fragment): FavVideoViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = FavVideoRepository(FavVideoDao())
            @Suppress("UNCHECKED_CAST")
            return FavVideoViewModel(respository) as T
        }
    })[FavVideoViewModel::class.java]
}

fun getDownloadVideoViewModel(activity: FragmentActivity): DownloadVideoViewModel {
    return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = DownloadVideoRepository(DownloadVideoDao())
            @Suppress("UNCHECKED_CAST")
            return DownloadVideoViewModel(respository) as T
        }
    })[DownloadVideoViewModel::class.java]
}

fun getDownloadVideoViewModel(fragment: Fragment): DownloadVideoViewModel {
    return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val respository = DownloadVideoRepository(DownloadVideoDao())
            @Suppress("UNCHECKED_CAST")
            return DownloadVideoViewModel(respository) as T
        }
    })[DownloadVideoViewModel::class.java]
}


