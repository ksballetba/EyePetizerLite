package com.ksballetba.eyetonisher.utilities

import android.content.res.Resources
import android.util.TypedValue
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import java.lang.reflect.Field
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun parseDuration(duration:Int):String{
    val min = duration/60
    val second = if(duration%60<10) "0${duration%60}" else (duration%60).toString()
    return "$min:$second"
}

fun setTabWidth(tabs: TabLayout, leftDip:Float, rightDip:Float){
    tabs.post {
        try {
            val tabLayout = tabs::class.java
            var tabStrip: Field?
            tabStrip = tabLayout.getDeclaredField("slidingTabIndicator")
            tabStrip.isAccessible = true
            var llTab: LinearLayout? = null
            llTab = tabStrip.get(tabs) as LinearLayout
            val left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,leftDip, Resources.getSystem().displayMetrics)
            val right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,rightDip, Resources.getSystem().displayMetrics)
            for (i in 0 until llTab.childCount){
                val child = llTab.getChildAt(i)
                child.setPadding(0,0,0,0)
                val params = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f)
                params.leftMargin = left.toInt()
                params.rightMargin = right.toInt()
                child.layoutParams = params
                child.invalidate()
            }
        } catch (e:NoSuchFieldException){
            e.printStackTrace()
        } catch (e:IllegalAccessException){
            e.printStackTrace()
        }
    }
}

fun runOnIoThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}

fun createFavVideo(videoInfo:VideoInfoBean):FavVideoBean{
    val favVideo = FavVideoBean(
            videoInfo.id,
            videoInfo.cover.detail,
            videoInfo.author.icon,
            videoInfo.author.name,
            videoInfo.title,
            videoInfo.category,
            videoInfo.duration,
            videoInfo.playUrl,
            videoInfo.description,
            videoInfo.consumption.collectionCount,
            videoInfo.consumption.replyCount,
            videoInfo.slogan
            )
    return favVideo
}
