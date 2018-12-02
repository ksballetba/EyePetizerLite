package com.ksballetba.eyetonisher.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.CategotyPlaylistBean
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import com.ksballetba.eyetonisher.ui.acitvities.CategoryActivity
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.widgets.BannerItemDecoration
import com.ksballetba.eyetonisher.utilities.createDownloadVideo
import com.ksballetba.eyetonisher.utilities.createFavVideo
import com.ksballetba.eyetonisher.utilities.getFavVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.DownloadVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import de.hdodenhof.circleimageview.CircleImageView

class CategoryPlaylistAdapter(val mItems: MutableList<CategotyPlaylistBean.Item>,val mOnClickListener: HomeAdapter.ItemOnClickListener,val mDBViewModel: FavVideoViewModel,val mDownloadVideoViewModel: DownloadVideoViewModel,val activity:CategoryActivity) : RecyclerView.Adapter<CategoryPlaylistAdapter.ViewHolder>() {
    internal var mContext: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playlistItem = view.findViewById<LinearLayout>(R.id.playlist_item)
        val playlistItemRec = view.findViewById<RecyclerView>(R.id.playlist_item_rec)
        val playlistItemAction = view.findViewById<ImageView>(R.id.playlist_item_action)
        val playlistItemAvatar = view.findViewById<CircleImageView>(R.id.playlist_item_avatar)
        val playlistItemTitle = view.findViewById<TextView>(R.id.playlist_item_title)
        val playlistItemCategory = view.findViewById<TextView>(R.id.playlist_item_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_playlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(model: CategotyPlaylistBean.Item) {
            Glide.with(mContext!!).load(model.data.header.icon).into(holder.playlistItemAvatar)
            holder.playlistItemTitle.text = model.data.header.title
            holder.playlistItemCategory.text = model.data.header.description
            val layoutManager = LinearLayoutManager(mContext)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            val videoList = model.data.itemList.toMutableList()
            val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
                override fun onDetailClick(idx: Int) {
                    navigateToPlayDetail(videoList[idx].data.id,videoList[idx].data.playUrl,videoList[idx].data.title,videoList[idx].data.cover.detail)
                }
                override fun onActionClick(idx: Int) {
                    showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action),videoList[idx].data)
                }
            }
            val videoAdapter = CategoryPlaylistItemAdapter(videoList,itemOnClickListener)
            holder.playlistItemRec.adapter = videoAdapter
            holder.playlistItemRec.layoutManager = layoutManager
            holder.playlistItemRec.addItemDecoration(BannerItemDecoration())
            holder.playlistItemRec.onFlingListener = null
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(holder.playlistItemRec)
            holder.playlistItemRec.setOnTouchListener { view, motionEvent ->
                when(motionEvent.action){
                    MotionEvent.ACTION_MOVE->{
                        view.parent.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    MotionEvent.ACTION_CANCEL->{
                        view.parent.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
                false
            }
            with(holder.playlistItemAction){
                mOnClickListener.onActionClick(position)
            }
        }
        val item = mItems[position]
        bind(item)
    }

    override fun getItemCount() = mItems.size

    fun update(newData: List<CategotyPlaylistBean.Item>) {
        mItems.clear()
        mItems.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(addData: List<CategotyPlaylistBean.Item>) {
        mItems.addAll(addData)
        notifyItemRangeInserted(mItems.size-addData.size,mItems.size-1)
    }


    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(mContext, PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        mContext?.startActivity(intent)
    }

    private fun showPopMenu(view: View,video: VideoInfoBean){
        val popupMenu = PopupMenu(mContext,view,Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{
                    mDBViewModel.insertVideo(createFavVideo(video))
                }
                R.id.action_download->{
                    if (activity.mDownloadBinder != null) {
                        activity.mDownloadBinder!!.startDownload(video.playUrl,video.title)
                    }
                    mDownloadVideoViewModel.insertVideo(createDownloadVideo(video))
                }
            }
            true
        }
    }
}
