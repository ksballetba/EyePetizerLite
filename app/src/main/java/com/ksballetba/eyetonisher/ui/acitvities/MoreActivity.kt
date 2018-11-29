package com.ksballetba.eyetonisher.ui.acitvities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.services.DownloadService
import com.ksballetba.eyetonisher.ui.fragments.RecyclerviewFragment
import kotlinx.android.synthetic.main.activity_more.*

class MoreActivity : AppCompatActivity() {

    val mFragmentList = mutableListOf<Fragment>()
    private lateinit var mFragment: RecyclerviewFragment

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
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimary)
        setContentView(R.layout.activity_more)
        setSupportActionBar(more_toolbar)
        supportActionBar?.title = ""
        initFragment()
        initService()
    }

    private fun initFragment(){
        mFragment = RecyclerviewFragment()
        val initType = intent.getStringExtra("init_type")
        when(initType){
            "topiclist"->{
                more_title.text = "专题"
            }
            "catelist"->{
                more_title.text = "分类"
            }
            "recolist"->{
                more_title.text = "推荐"
            }
        }
        val bundle = Bundle()
        bundle.putString("init_type",initType)
        mFragment.arguments = bundle
        mFragmentList.add(mFragment)
        more_viewpager.adapter = ViewPagerAdapter(mFragmentList,supportFragmentManager)
    }

    private fun initService(){
        val intent = Intent(this,DownloadService::class.java)
        startService(intent)
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
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
