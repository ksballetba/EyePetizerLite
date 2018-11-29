package com.ksballetba.eyetonisher.ui.acitvities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.TopicInfoBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.services.DownloadService
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.TopicDetailVideoAdapter
import com.ksballetba.eyetonisher.ui.widgets.AppBarStateChangeListener
import com.ksballetba.eyetonisher.ui.widgets.HeaderItemDecoration
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.utilities.createFavVideo
import com.ksballetba.eyetonisher.utilities.getFavVideoViewModel
import com.ksballetba.eyetonisher.utilities.getHomeViewModel
import com.ksballetba.eyetonisher.utilities.getTopicDetailViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.TopicDetailViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_topic.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast

class TopicActivity : AppCompatActivity() {
    private lateinit var mViewModel:TopicDetailViewModel
    private lateinit var mDBViewModel:FavVideoViewModel
    var mList = mutableListOf<TopicInfoBean.Item>()
    private lateinit var mAdapter: TopicDetailVideoAdapter

    var mDownloadBinder: DownloadService.DownloadBinder? = null

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mDownloadBinder = p1 as DownloadService.DownloadBinder
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_topic)
        initToolbar()
        initRec()
        initService()
    }

    private fun initToolbar(){
        setSupportActionBar(topic_toolbar)
        supportActionBar?.title = ""
        topic_appbarlayout.addOnOffsetChangedListener(AppBarStateChangeListener{ appBarLayout, state ->
            when(state){
                AppBarStateChangeListener.State.COLLAPSED->{
                    topic_coll_title.visibility = View.VISIBLE
                }
                AppBarStateChangeListener.State.EXPANDED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                    topic_coll_title.visibility = View.GONE
                }
                AppBarStateChangeListener.State.IDLE->{
                    topic_coll_title.visibility = View.GONE
                }
            }
        })
    }


    private fun initRec() {
        val id = intent.getIntExtra("topic_id",0)
        mViewModel = getTopicDetailViewModel(this,id)
        mDBViewModel = getFavVideoViewModel(this)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        topic_video_rec.layoutManager = layoutManager
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mList[idx].data.header.id,mList[idx].data.content.data.playUrl,mList[idx].data.content.data.title,mList[idx].data.content.data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action),mList[idx].data.content.data)
            }
        }
        mAdapter = TopicDetailVideoAdapter(mList,itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(mAdapter)
        animationAdapter.setFirstOnly(false)
        topic_video_rec.adapter = animationAdapter
        topic_video_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        topic_video_rec.addItemDecoration(MarginDividerItemDecoration(0f,0f,this))
        mViewModel.fetchData().observe(this, Observer {
            topic_coll_title.text = it.brief
            topic_brief.text = it.brief
            topic_sum.text = it.text
            Glide.with(this).load(it.headerImage).transition(DrawableTransitionOptions.withCrossFade(500)).into(topic_bg)
            mList = it.itemList.toMutableList()
            mAdapter.update(mList)
        })
    }

    private fun initService(){
        val intent = Intent(this,DownloadService::class.java)
        startService(intent)
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }

    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(this,PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View,video: VideoInfoBean){
        val popupMenu = PopupMenu(this,view,Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{
                    mDBViewModel.insertVideo(createFavVideo(video))
                }
                R.id.action_download->{
                    downloadVideo(video.playUrl,video.title)
                }
            }
            true
        }
    }

    private fun downloadVideo(url:String,fileName:String){
        if (mDownloadBinder != null) {
            mDownloadBinder!!.startDownload(url,fileName)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
