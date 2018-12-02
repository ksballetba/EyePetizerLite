package com.ksballetba.eyetonisher.ui.acitvities

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.services.DownloadService
import com.ksballetba.eyetonisher.ui.fragments.*
import kotlinx.android.synthetic.main.main_activity.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSISON_CODE = 100
    }

    lateinit var mHomeFragment: HomeFragment
    lateinit var mExplorerFragment: ExplorerFragment
    lateinit var mHotFragment: HotFragment
    val mFragmentList = ArrayList<Fragment>()
    var mDownloadBinder:DownloadService.DownloadBinder? = null

    private val connection = object : ServiceConnection{
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
        window.statusBarColor = Color.TRANSPARENT
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        setContentView(R.layout.main_activity)
        initFragment()
        initUiKit()
        requestPermissions()
    }



    private fun initUiKit() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.title = "首页"
        main_bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    supportActionBar?.title = "首页"
                    main_viewpager.currentItem = 0
                    rank_tablayout.visibility = View.GONE
                }
                R.id.menu_hot -> {
                    supportActionBar?.title = "热门"
                    main_viewpager.currentItem = 1
                    rank_tablayout.visibility = View.VISIBLE
                }
                R.id.menu_explorer -> {
                    supportActionBar?.title = "发现"
                    main_viewpager.currentItem = 2
                    rank_tablayout.visibility = View.GONE

                }
            }
            true
        }
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_favorite->{
                    navigationToFavAndDownload("fav")
                }
                R.id.nav_dowmload->{
                    navigationToFavAndDownload("download")
                }
            }
            true
        }
    }

    private fun initFragment() {
        mHomeFragment = HomeFragment()
        mHotFragment = HotFragment()
        mExplorerFragment = ExplorerFragment()
        mFragmentList.add(mHomeFragment)
        mFragmentList.add(mHotFragment)
        mFragmentList.add(mExplorerFragment)
        main_viewpager.offscreenPageLimit = 3
        main_viewpager.adapter = ViewPagerAdapter(mFragmentList, supportFragmentManager)
    }

    private fun navigationToFavAndDownload(initType:String){
        val intent = Intent(this,FavAndDownloadActivity::class.java)
        intent.putExtra("init_type",initType)
        startActivity(intent)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(MainActivity.PERMISSISON_CODE)
    private fun requestPermissions(){
        if(EasyPermissions.hasPermissions(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            val intent = Intent(this,DownloadService::class.java)
            startService(intent)
            bindService(intent,connection, Context.BIND_AUTO_CREATE)
        }
        else{
            EasyPermissions.requestPermissions(this, "需要获取权限",
                    MainActivity.PERMISSISON_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

class ViewPagerAdapter(var mList: List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

}