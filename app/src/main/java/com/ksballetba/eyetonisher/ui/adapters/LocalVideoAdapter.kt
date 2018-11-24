package com.ksballetba.eyetonisher.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.utilities.parseDuration
import de.hdodenhof.circleimageview.CircleImageView

class LocalVideoAdapter(val mItems:MutableList<FavVideoBean>,val mOnClickListener: HomeAdapter.ItemOnClickListener): RecyclerView.Adapter<LocalVideoAdapter.ViewHolder>(){
    internal var mContext: Context? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val videoItem = view.findViewById<FrameLayout>(R.id.video_item)
        val videoItemCover = view.findViewById<ImageView>(R.id.video_item_cover)
        val videoItemDuration = view.findViewById<TextView>(R.id.video_item_duration)
        val videoItemAvatar = view.findViewById<CircleImageView>(R.id.video_item_avatar)
        val videoItemTitle = view.findViewById<TextView>(R.id.video_item_title)
        val videoItemCategory = view.findViewById<TextView>(R.id.video_item_category)
        val videoItemAction = view.findViewById<ImageView>(R.id.video_item_action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_video_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        fun bind(model: FavVideoBean){
            val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            Glide.with(mContext!!).load(model.cover).apply(options).transition(DrawableTransitionOptions.withCrossFade(500)).into(holder.videoItemCover)
            Glide.with(mContext!!).load(model.authorAvater).into(holder.videoItemAvatar)
            holder.videoItemDuration.text = parseDuration(model.duration!!)
            holder.videoItemTitle.text = model.title
            holder.videoItemCategory.text = model.category
            with(holder.videoItem){
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

    fun update(newData:List<FavVideoBean>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData:List<FavVideoBean>){
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }

    fun delete(deleteItemIdx:Int){
        mItems.removeAt(deleteItemIdx)
        notifyItemRemoved(deleteItemIdx)
        notifyItemRangeChanged(deleteItemIdx,mItems.size)
    }
}