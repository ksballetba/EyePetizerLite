package com.ksballetba.eyetonisher.ui.behaviors

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.blankj.utilcode.util.ConvertUtils
import com.google.android.material.appbar.AppBarLayout

class CardViewScrollBehavior : CoordinatorLayout.Behavior<View> {

    val appBarHeight = 280

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val alpha = (dependency.bottom - appBarHeight).toFloat() / (dependency.height - appBarHeight)
        child.alpha = alpha
        child.translationY = -((1 - alpha) * child.height) / 2
        return true
    }
}