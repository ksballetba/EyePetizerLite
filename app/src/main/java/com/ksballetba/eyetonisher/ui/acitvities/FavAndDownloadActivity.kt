package com.ksballetba.eyetonisher.ui.acitvities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.FavVideoBean
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.LocalVideoAdapter
import com.ksballetba.eyetonisher.utilities.createFavVideo
import com.ksballetba.eyetonisher.utilities.getFavVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_fav_and_download.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavAndDownloadActivity : AppCompatActivity() {

    private lateinit var mFavViewModel:FavVideoViewModel
    private lateinit var mFavAdapter:LocalVideoAdapter

    var mFavList = mutableListOf<FavVideoBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimary)
        setContentView(R.layout.activity_fav_and_download)
        setSupportActionBar(fav_dowmload_toolbar)
        supportActionBar?.title = ""
        initFavRec()
    }

    private fun initFavRec(){
        mFavViewModel = getFavVideoViewModel(this)
        fav_dowmload_title.text = "我喜欢的"

        val layoutManager = LinearLayoutManager(this)
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mFavList[idx].videoId,mFavList[idx].playUrl,mFavList[idx].title,mFavList[idx].cover)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action),idx)
                Log.d("debug",idx.toString())
            }
        }
        mFavAdapter = LocalVideoAdapter(mFavList,itemOnClickListener)
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

    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(this,PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View,idx:Int){
        val popupMenu = PopupMenu(this,view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.menu[0].title = "取消收藏"
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{
                    mFavViewModel.deleteVideo(mFavList[idx].videoId)
                    mFavAdapter.delete(idx)
                }
                R.id.action_download->{

                }
            }
            true
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
