package com.ksballetba.eyetonisher.ui.widgets

import android.animation.ValueAnimator
import android.graphics.*
import android.text.TextPaint
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.addListener
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils

class HeaderItemDecoration : RecyclerView.ItemDecoration {

    private var textPaint: TextPaint
    private var callback: DecorationCallback
    private var fontMetrics: Paint.FontMetrics

    val leftMargin = ConvertUtils.dp2px(12.toFloat()).toFloat()
    val topMargin = ConvertUtils.sp2px(32.toFloat())
    val titleMargin = ConvertUtils.dp2px(12.toFloat())

    constructor(decorationCallback: DecorationCallback) {
        callback = decorationCallback
        textPaint = TextPaint()
        textPaint.textSize = ConvertUtils.sp2px(22.toFloat()).toFloat()
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.isAntiAlias = true
        textPaint.color = Color.BLACK
        fontMetrics = Paint.FontMetrics()
        textPaint.getFontMetrics(fontMetrics)
        textPaint.textAlign = Paint.Align.LEFT
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val title = callback.getGroupFirstTitle(position)
            if (callback.isGroupFirst(position)) {
                val baseline = (view.top - parent.marginTop - titleMargin).toFloat()
                c.drawText(title, leftMargin, baseline, textPaint)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.top = (1.5 * topMargin).toInt()
        } else if (callback.isGroupFirst(position)) {
            outRect.top = topMargin
        } else {
            outRect.top = 0
        }
    }

    interface DecorationCallback {
        fun isGroupFirst(position: Int): Boolean
        fun getGroupFirstTitle(position: Int): String
    }
}