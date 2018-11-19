package com.ksballetba.eyetonisher.ui.acitvities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.source.remote.RankRespository
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.fragments.*
import com.ksballetba.eyetonisher.ui.widgets.FadePageTransformer
import com.ksballetba.eyetonisher.viewmodel.RankViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    lateinit var mHomeFragment: HomeFragment
    lateinit var mExplorerFragment: ExplorerFragment
    lateinit var mHotFragment: HotFragment
    val mFragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimary)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        setContentView(R.layout.main_activity)
        initFragment()
        initUiKit()
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


}

class ViewPagerAdapter(var mList: List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

}