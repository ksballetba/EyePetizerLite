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
import com.ksballetba.eyetonisher.data.bean.CateListBean

class CateAdapter(val mItems: MutableList<CateListBean.Item>, internal val didSelectedAtPos: (idx: Int) -> Unit) : RecyclerView.Adapter<CateAdapter.ViewHolder>() {
    internal var mContext: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cateItem = view.findViewById<CardView>(R.id.cate_item)
        val cateItemCover = view.findViewById<ImageView>(R.id.cate_item_cover)
        val cateItemName= view.findViewById<TextView>(R.id.cate_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_cate_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model: CateListBean.Item) {
            val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            Glide.with(mContext!!).load(model.data.image).apply(options).transition(DrawableTransitionOptions.withCrossFade(1000)).into(holder.cateItemCover)
            holder.cateItemName.text = model.data.title
            with(holder.cateItem) {
                setOnClickListener {
                    didSelectedAtPos(position)
                }
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount() = mItems.size

    fun update(newData: List<CateListBean.Item>) {
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData: List<CateListBean.Item>) {
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }
}