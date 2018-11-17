package com.ksballetba.eyetonisher.ui.widgets

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils.dp2px
import com.blankj.utilcode.util.PhoneUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils

class BannerItemDecoration:RecyclerView.ItemDecoration(){

    var screenWidth = ScreenUtils.getScreenWidth()
    var mLeftPageVisibleWidth = 16f
    var mPageMargin = 2f
    //第一张图片的左边距

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val positon = parent.getChildAdapterPosition(view) //获得当前item的position
        val itemCount = parent.adapter?.itemCount //获得item的数量
        val leftMargin = if (positon == 0) dp2px(mLeftPageVisibleWidth) else dp2px(mPageMargin) //如果position为0，设置leftMargin为计算后边距，其他为默认边距
        val rightMargin = if (positon == (itemCount!! - 1)) dp2px(mLeftPageVisibleWidth) else dp2px(mPageMargin) //同上，设置最后一张图片
        val lp = view.layoutParams as RecyclerView.LayoutParams
        lp.width = screenWidth - 2*dp2px(mLeftPageVisibleWidth)
        lp.setMargins(leftMargin, 0, rightMargin, 0) //30和60分别是item到上下的margin
        view.layoutParams = lp //设置参数
        super.getItemOffsets(outRect, view, parent, state)
    }
}