package com.ksballetba.eyetonisher.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.ksballetba.eyetonisher.R

class MarginDividerItemDecoration : RecyclerView.ItemDecoration {

    private val mHeightMargin = 8f
    private var mLeftMargin: Float
    private var mRightMargin: Float
//    private var mDecorationCallback:HeaderItemDecoration.DecorationCallback

    private var mPaint: Paint

    constructor(leftMargin: Float, rightMargin: Float, context: Context) {
        mLeftMargin = leftMargin
        mRightMargin = rightMargin
//        mDecorationCallback = decorationCallback
        mPaint = Paint()
        mPaint.color = ContextCompat.getColor(context, R.color.divider)
        mPaint.alpha = 75
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val param = view.layoutParams as RecyclerView.LayoutParams
            val baseY = view.bottom.toFloat()
            val startX = (view.left + ConvertUtils.dp2px(mLeftMargin)).toFloat()
            val endX = (view.right - ConvertUtils.dp2px(mRightMargin)).toFloat()
            c.drawLine(startX, baseY, endX, baseY, mPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
    }

}