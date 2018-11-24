package com.ksballetba.eyetonisher.ui.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import org.jetbrains.anko.matchParent

class CategoryAdapterItemDecoration: RecyclerView.ItemDecoration(){

    val itemHeight = (ScreenUtils.getScreenWidth()-ConvertUtils.dp2px(8f))/2
    val topMargin = ConvertUtils.dp2px(22f)+ConvertUtils.sp2px(22f)
    //第一张图片的左边距

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildAdapterPosition(view)
        val lp = view.layoutParams as RecyclerView.LayoutParams
        lp.width = matchParent
        lp.height = itemHeight
        view.layoutParams = lp //设置参数
        super.getItemOffsets(outRect, view, parent, state)
    }
}