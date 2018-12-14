package com.ksballetba.eyetonisher.ui.acitvities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.FileUtils
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.DownloadVideoBean
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.services.DownloadService
import com.ksballetba.eyetonisher.ui.adapters.DownloadVideoAdapter
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.FavVideoAdapter
import com.ksballetba.eyetonisher.utilities.*
import com.ksballetba.eyetonisher.viewmodel.DownloadVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.activity_fav_and_download.*
import java.io.File

class FavAndDownloadActivity : AppCompatActivity() {

    private lateinit var mFavViewModel: FavVideoViewModel
    private lateinit var mDownloadViewModel: DownloadVideoViewModel
    private lateinit var mFavAdapter: FavVideoAdapter
    private lateinit var mDownloadAdapter: DownloadVideoAdapter

    var mFavList = mutableListOf<FavVideoBean>()
    var mDownloadList = mutableListOf<DownloadVideoBean>()

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
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_fav_and_download)
        setSupportActionBar(fav_dowmload_toolbar)
        supportActionBar?.title = ""
        initService()
        mFavViewModel = getFavVideoViewModel(this)
        mDownloadViewModel = getDownloadVideoViewModel(this)
        when (intent.getStringExtra("init_type")) {
            "fav" -> {
                initFavRec()
            }
            "download" -> {
                initDownloadRec()
            }
        }
    }

    private fun initFavRec() {

        fav_dowmload_title.text = "我喜欢的"
        val layoutManager = LinearLayoutManager(this)
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mFavList[idx].videoId!!, mFavList[idx].playUrl!!, mFavList[idx].title!!, mFavList[idx].cover!!)
            }

            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action), idx)
                Log.d("debug", idx.toString())
            }
        }
        mFavAdapter = FavVideoAdapter(mFavList, itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(mFavAdapter)
        animationAdapter.setFirstOnly(false)
        fav_dowmload_rec.adapter = animationAdapter
        fav_dowmload_rec.itemAnimator = SlideInLeftAnimator(OvershootInterpolator(1f))
        layoutManager.orientation = RecyclerView.VERTICAL
        fav_dowmload_rec.layoutManager = layoutManager
        mFavViewModel.getVideos().observe(this, Observer {
            mFavList = it.reversed().toMutableList()
            mFavAdapter.update(mFavList)
        })
    }

    private fun initDownloadRec() {
        fav_dowmload_title.text = "我的缓存"
        val layoutManager = LinearLayoutManager(this)
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mDownloadList[idx].videoId!!, mDownloadList[idx].playUrl!!, mDownloadList[idx].title!!, mDownloadList[idx].cover!!)
            }

            override fun onActionClick(idx: Int) {
                showDownloadPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action), idx)
                Log.d("debug", idx.toString())
            }
        }
        mDownloadAdapter = DownloadVideoAdapter(mDownloadList, itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(mDownloadAdapter)
        animationAdapter.setFirstOnly(false)
        fav_dowmload_rec.adapter = animationAdapter
        fav_dowmload_rec.itemAnimator = SlideInLeftAnimator(OvershootInterpolator(1f))
        layoutManager.orientation = RecyclerView.VERTICAL
        fav_dowmload_rec.layoutManager = layoutManager
        val fileDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).path+"/eyetonisher/")
        if(!fileDir.exists()){
            fileDir.mkdir()
        }
        val fileList = FileUtils.listFilesInDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).path + "/eyetonisher/")
        fileList.sortBy {
            it.lastModified()
        }
        val fileReverseList = fileList.asReversed()
        val fileNameList = mutableListOf<String>()
        val filePlayUrlList = mutableListOf<String>()
        if (fileReverseList.size > 0) {
            fileReverseList.forEach {
                fileNameList.add(it.name.substring(0, it.name.length - 4))
                filePlayUrlList.add(it.absolutePath)
            }
        }
        mDownloadViewModel.getVideos().observe(this, Observer {
            mDownloadList = it.reversed().filter {
                fileNameList.contains(it.title)
            }.toMutableList()
            for (i in 0 until mDownloadList.size) {
                mDownloadList[i].playUrl = filePlayUrlList[i]
            }
            mDownloadAdapter.update(mDownloadList)
        })
    }

    private fun navigateToPlayDetail(videoId: Int, videoUrl: String, videoTitle: String, videoThumb: String) {
        val intent = Intent(this, PlayDetailActivity::class.java)
        intent.putExtra("video_url", videoUrl)
        intent.putExtra("video_id", videoId)
        intent.putExtra("video_title", videoTitle)
        intent.putExtra("video_thumb", videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View, idx: Int) {
        val popupMenu = PopupMenu(this, view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.menu[0].title = "取消收藏"
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_fav -> {
                    mFavViewModel.deleteVideo(mFavList[idx].videoId!!)
                    mFavAdapter.delete(idx)
                }
                R.id.action_download -> {
                    downloadVideo(mFavList[idx].playUrl!!, mFavList[idx].title!!)
                    mDownloadViewModel.insertVideo(parseDownloadVideo(mFavList[idx]))
                }
            }
            true
        }
    }

    private fun showDownloadPopMenu(view: View, idx: Int) {
        val popupMenu = PopupMenu(this, view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.menu[1].title = "删除文件"
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_fav -> {
                    mFavViewModel.insertVideo(parseFavVideo(mDownloadList[idx]))
                }
                R.id.action_download -> {
                    mDownloadViewModel.deleteVideo(mDownloadList[idx].videoId!!)
                    mDownloadAdapter.delete(idx)
                    val filelist = FileUtils.listFilesInDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).path + "/eyetonisher/")
                    for (i in 0 until filelist.size) {
                        if (filelist[i].name.substring(0, filelist[i].name.length - 4) == mDownloadList[idx].title) {
                            filelist[i].delete()
                        }
                    }
                }
            }
            true
        }
    }

    private fun downloadVideo(url: String, fileName: String) {
        if (mDownloadBinder != null) {
            mDownloadBinder!!.startDownload(url, fileName)
        }
    }

    private fun initService() {
        val intent = Intent(this, DownloadService::class.java)
        startService(intent)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
