package com.ksballetba.eyetonisher.ui.widgets

import android.view.View
import androidx.viewpager.widget.ViewPager

class FadePageTransformer : ViewPager.PageTransformer {


    override fun transformPage(page: View, position: Float) {
        when(position){
            !in -1f..1f->{
                page.alpha = 0f
            }
            0f->{
                page.alpha = 1f
            }
            else->{
                page.alpha = 1.0f - Math.abs(position)
            }
        }
    }
}