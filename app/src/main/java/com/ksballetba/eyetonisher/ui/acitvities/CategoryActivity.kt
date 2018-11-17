package com.ksballetba.eyetonisher.ui.acitvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.ui.fragments.CategoryDetailFragment
import com.ksballetba.eyetonisher.ui.widgets.AppBarStateChangeListener
import com.ksballetba.eyetonisher.utilities.setTabWidth
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    val mFragmentList = mutableListOf<Fragment>()
    private lateinit var mHotVideoFragment: CategoryDetailFragment
    private lateinit var mAllVideoFragment: CategoryDetailFragment
    private lateinit var mPlaylistFragment: CategoryDetailFragment
    private lateinit var mProviderListFragment: CategoryDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContentView(R.layout.activity_category)
        initToolbar()
        initFragments()
    }

    private fun initToolbar(){
        setSupportActionBar(category_toolbar)
        supportActionBar?.title = ""
        category_title.text = intent.getStringExtra("category_title")
        category_coll_title.text = intent.getStringExtra("category_title")
        category_slogan.text = "优雅的行走在潮流尖端"
        category_appbarlayout.addOnOffsetChangedListener(AppBarStateChangeListener{appBarLayout, state ->
            when(state){
                AppBarStateChangeListener.State.COLLAPSED->{
                    category_coll_title.visibility = View.VISIBLE
                }
                AppBarStateChangeListener.State.EXPANDED->{
                    actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                    category_coll_title.visibility = View.GONE
                }
                AppBarStateChangeListener.State.IDLE->{
                    category_coll_title.visibility = View.GONE
                }
            }
        })
    }

    private fun initFragments(){
        mHotVideoFragment = CategoryDetailFragment()
        mAllVideoFragment = CategoryDetailFragment()
        mPlaylistFragment = CategoryDetailFragment()
        mProviderListFragment = CategoryDetailFragment()

        val hotBundle = Bundle()
        hotBundle.putString("init_type","hotvideolist")
        mHotVideoFragment.arguments = hotBundle

        val allBundle = Bundle()
        allBundle.putString("init_type","allvideolist")
        mAllVideoFragment.arguments = allBundle

        val playlistBundle = Bundle()
        playlistBundle.putString("init_type","playlist")
        mPlaylistFragment.arguments = playlistBundle

        val providersBundle = Bundle()
        providersBundle.putString("init_type","providerlist")
        mProviderListFragment.arguments = providersBundle
        setTabWidth(category_tablayout,30f,30f)
        mFragmentList.add(mHotVideoFragment)
        mFragmentList.add(mAllVideoFragment)
        mFragmentList.add(mPlaylistFragment)
        mFragmentList.add(mProviderListFragment)
        category_viewpager.adapter = ViewPagerAdapter(mFragmentList,supportFragmentManager)
        category_tablayout.setupWithViewPager(category_viewpager)
        category_tablayout.getTabAt(0)?.text = "热门"
        category_tablayout.getTabAt(1)?.text = "全部"
        category_tablayout.getTabAt(2)?.text = "专辑"
        category_tablayout.getTabAt(3)?.text = "作者"
    }
}
