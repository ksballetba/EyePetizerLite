package com.ksballetba.eyetonisher.utilities

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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

fun getTopicViewModel(activity: FragmentActivity): TopicViewModel {
    return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
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