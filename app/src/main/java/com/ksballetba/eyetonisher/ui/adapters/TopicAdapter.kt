package com.ksballetba.eyetonisher.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.TopicListBean

class TopicAdapter(val mItems: MutableList<TopicListBean.Item>, internal val didSelectedAtPos: (idx: Int) -> Unit) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    internal var mContext: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topicItem = view.findViewById<CardView>(R.id.banner_item)
        val topicItemCover = view.findViewById<ImageView>(R.id.banner_item_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model: TopicListBean.Item) {
            val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            Glide.with(mContext!!).load(model.data.image).apply(options).transition(DrawableTransitionOptions.withCrossFade(500)).into(holder.topicItemCover)
            with(holder.topicItem) {
                setOnClickListener {
                    didSelectedAtPos(position)
                }
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount() = mItems.size

    fun update(newData: List<TopicListBean.Item>) {
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData: List<TopicListBean.Item>) {
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }
}