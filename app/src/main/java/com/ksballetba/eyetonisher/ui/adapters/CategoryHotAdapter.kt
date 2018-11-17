package com.ksballetba.eyetonisher.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.CategoryHomeListBean
import com.ksballetba.eyetonisher.utilities.parseDuration
import de.hdodenhof.circleimageview.CircleImageView

class CategoryHotAdapter(val mItems: MutableList<CategoryHomeListBean.Item>,val mOnClickListener: HomeAdapter.ItemOnClickListener) : RecyclerView.Adapter<CategoryHotAdapter.ViewHolder>() {
    internal var mContext: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoItem = view.findViewById<CardView>(R.id.video_item)
        val videoItemCover = view.findViewById<ImageView>(R.id.video_item_cover)
        val videoItemAction = view.findViewById<ImageView>(R.id.video_item_action)
        val videoItemDuration = view.findViewById<TextView>(R.id.video_item_duration)
        val videoItemAvatar = view.findViewById<CircleImageView>(R.id.video_item_avatar)
        val videoItemTitle = view.findViewById<TextView>(R.id.video_item_title)
        val videoItemCategory = view.findViewById<TextView>(R.id.video_item_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model: CategoryHomeListBean.Item) {
            val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            Glide.with(mContext!!).load(model.data.cover.detail).apply(options).transition(DrawableTransitionOptions.withCrossFade(500)).into(holder.videoItemCover)
            Glide.with(mContext!!).load(model.data.author.icon).into(holder.videoItemAvatar)
            holder.videoItemDuration.text = parseDuration(model.data.duration)
            holder.videoItemTitle.text = model.data.title
            holder.videoItemCategory.text = "${model.data.author.name} / #${model.data.category}"
            with(holder.videoItem) {
                setOnClickListener {
                    mOnClickListener.onDetailClick(position)
                }
            }
            with(holder.videoItemAction){
                setOnClickListener {
                    mOnClickListener.onActionClick(position)
                }
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount() = mItems.size

    fun update(newData: List<CategoryHomeListBean.Item>) {
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData: List<CategoryHomeListBean.Item>) {
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }
}
