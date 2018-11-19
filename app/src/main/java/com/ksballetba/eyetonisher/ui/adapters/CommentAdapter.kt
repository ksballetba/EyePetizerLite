package com.ksballetba.eyetonisher.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.RepliesBean
import de.hdodenhof.circleimageview.CircleImageView

class CommentAdapter(val mItems:MutableList<RepliesBean.Item>, val mOnClickListener: HomeAdapter.ItemOnClickListener): RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    internal var mContext: Context? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val commentItem = view.findViewById<RelativeLayout>(R.id.comment_item)
        val commentItemAvatar = view.findViewById<CircleImageView>(R.id.comment_item_avatar)
        val commentItemUser = view.findViewById<TextView>(R.id.comment_item_user)
        val commentItemContent = view.findViewById<TextView>(R.id.comment_item_content)
        val commentItemLikeCount = view.findViewById<TextView>(R.id.comment_item_likecount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        if(mContext==null){
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_comment_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        fun bind(model: RepliesBean.Item){
            val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            if(model.data.user.avatar!=null)
                Glide.with(mContext!!).load(model.data.user.avatar).into(holder.commentItemAvatar)
            holder.commentItemUser.text = model.data.user.nickname
            holder.commentItemContent.text = model.data.message
            holder.commentItemLikeCount.text = model.data.likeCount.toString()
            with(holder.commentItem){
                setOnClickListener {
                    mOnClickListener.onDetailClick(position)
                }
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount() = mItems.size

    fun update(newData:List<RepliesBean.Item>){
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData:List<RepliesBean.Item>){
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }

}