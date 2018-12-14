package com.ksballetba.eyetonisher.ui.acitvities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.RelatedVideoBean
import com.ksballetba.eyetonisher.data.bean.RepliesBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import com.ksballetba.eyetonisher.services.DownloadService
import com.ksballetba.eyetonisher.ui.adapters.CommentAdapter
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.RelatedVideoAdapter
import com.ksballetba.eyetonisher.utilities.*
import com.ksballetba.eyetonisher.viewmodel.DownloadVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.VideoDetailViewModel
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_play_detail.*
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.jetbrains.anko.toast


class PlayDetailActivity : GSYBaseActivityDetail<StandardGSYVideoPlayer>() {
    private lateinit var videoUrl:String
    private lateinit var videoTitle:String
    private lateinit var videoThumb:String
    private var videoId = 0
    private lateinit var mVideoInfo: VideoInfoBean
    private lateinit var mViewModel:VideoDetailViewModel
    private lateinit var mDBViewModel:FavVideoViewModel
    private lateinit var mDownloadViewModel: DownloadVideoViewModel
    var mDownloadBinder: DownloadService.DownloadBinder? = null
    private var isDownloaded = false
    private var isLiked = false

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mDownloadBinder = p1 as DownloadService.DownloadBinder
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setContentView(R.layout.activity_play_detail)
        videoUrl = intent.getStringExtra("video_url")
        videoTitle = intent.getStringExtra("video_title")
        videoThumb = intent.getStringExtra("video_thumb")
        videoId = intent.getIntExtra("video_id",0)
        mViewModel = getVideoDetailViewModel(this,videoId)
        mDBViewModel = getFavVideoViewModel(this)
        mDownloadViewModel = getDownloadVideoViewModel(this)
        initBackButton()
        initVideoBuilderMode()
        initVideoInfo()
        initRecoRec()
        initCommentRec()
        initService()
        video_player.startButton.performClick()
    }


    private fun initVideoInfo(){
        mViewModel.fetchInfo().observe(this,Observer {
            playdetail_title.text = it.title
            playdetail_category.text = it.category
            playdetail_refer.text = it.description
            playdetail_favcount.text = it.consumption.collectionCount.toString()
            playdetail_commentscount.text = it.consumption.replyCount.toString()
            Glide.with(this).load(it.author.icon).into(playdetail_avatar)
            playdetail_authorname.text = it.author.name
            playdetail_authorslogan.text = it.author.description
            mVideoInfo = it
            playdetail_favbtn.setOnClickListener {
                if(isLiked){
                    mDBViewModel.deleteVideo(videoId)
                    playdetail_favbtn.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_grey_700_24dp))
                    isLiked = false
                }else{
                    playdetail_favbtn.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_700_24dp))
                    mDBViewModel.insertVideo(createFavVideo(mVideoInfo))
                    isLiked = true
                }
            }
            playdetail_downloadbtn.setOnClickListener {
                if(!isDownloaded){
                    mDownloadViewModel.insertVideo(createDownloadVideo(mVideoInfo))
                    mDownloadBinder!!.startDownload(mVideoInfo.playUrl,mVideoInfo.title)
                    playdetail_downloadcount.text = "缓存中..."
                }
            }
        })
    }
    
    private fun initRecoRec(){
        var recoList = mutableListOf<RelatedVideoBean.Item>()
        val layoutManager = LinearLayoutManager(this)
        val itemOnClickListener = object :HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(recoList[idx].data.id,recoList[idx].data.playUrl,recoList[idx].data.title,recoList[idx].data.cover.detail)
            }

            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action),recoList[idx].data)
            }
        }
        val recoAdapter = RelatedVideoAdapter(recoList,itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(recoAdapter)
        animationAdapter.setFirstOnly(false)
        layoutManager.orientation = RecyclerView.VERTICAL
        playdetail_recorec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        playdetail_recorec.layoutManager = layoutManager
        playdetail_recorec.adapter = animationAdapter
        mViewModel.fetchRelatedVideo().observe(this, Observer {
            recoList = it.filter {
                it.type == "videoSmallCard"
            }.toMutableList()
            recoAdapter.update(recoList)
        })
    }

    private fun initCommentRec(){
        var commentList = mutableListOf<RepliesBean.Item>()
        val layoutManager = LinearLayoutManager(this)
        val itemOnClickListener = object :HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {

            }
            override fun onActionClick(idx: Int) {

            }
        }
        val commentAdapter = CommentAdapter(commentList,itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(commentAdapter)
        animationAdapter.setFirstOnly(false)
        layoutManager.orientation = RecyclerView.VERTICAL
        playdetail_commentsrec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        playdetail_commentsrec.layoutManager = layoutManager
        playdetail_commentsrec.adapter = animationAdapter
        playdetail_commentsrec.addItemDecoration(MarginDividerItemDecoration(56f,0f,this))
        mViewModel.fetchReplies().observe(this, Observer {
            commentList = it.filter {
                it.type == "reply"
            }.toMutableList()
            commentAdapter.update(commentList)
        })
    }
    private fun initService(){
        val intent = Intent(this,DownloadService::class.java)
        startService(intent)
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }


    private fun initBackButton(){
        video_player.backButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_keyboard_arrow_down_white_36dp))
        video_player.backButton.setOnClickListener {
            finish()
        }
        playdetail_commentbtn.setOnClickListener {
           playdetail_scrollview.post {
               playdetail_scrollview.smoothScrollTo(0,playdetail_recorec.bottom+1000)
           }
        }
        mDBViewModel.isLiked(videoId).observe(this, Observer {
            if(it){
                playdetail_favbtn.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_700_24dp))
                isLiked = true
            }else{
                playdetail_favbtn.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_grey_700_24dp))
                isLiked = false
            }
        })
        mDownloadViewModel.isDownloaded(videoId).observe(this, Observer {
            if(it){
                playdetail_downloadcount.text = "已缓存"
                isDownloaded = true
            }else{
                playdetail_downloadcount.text = "缓存"
                isDownloaded = false
            }
        })

    }

    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(this,PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
        video_player.release()
        finish()
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
    }
    private fun showPopMenu(view: View,video:VideoInfoBean){
        val popupMenu = PopupMenu(this,view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{
                    mDBViewModel.insertVideo(createFavVideo(video))
                }
                R.id.action_download->{
                    downloadVideo(video.playUrl,video.title)
                    mDownloadViewModel.insertVideo(createDownloadVideo(video))
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


    override fun getGSYVideoPlayer(): StandardGSYVideoPlayer {
        return video_player
    }

     override fun getGSYVideoOptionBuilder(): GSYVideoOptionBuilder {
        val imageView = ImageView(this)
        Glide.with(this).load(videoThumb).into(imageView)
        return GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(videoUrl)
                .setCacheWithPlay(true)
                .setVideoTitle(videoTitle)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1f)
                .setAutoFullWithSize(true)
                .setDialogProgressColor(R.color.colorPrimary,R.color.divider)
    }

    override fun clickForFullScreen() {

    }

    override fun getDetailOrientationRotateAuto(): Boolean {
        return true
    }



}
