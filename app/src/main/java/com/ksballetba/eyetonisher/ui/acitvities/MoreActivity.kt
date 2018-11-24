package com.ksballetba.eyetonisher.ui.acitvities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.CateListBean
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.TopicListBean
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.ui.adapters.CateAdapter
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.TopicAdapter
import com.ksballetba.eyetonisher.ui.fragments.CategoryDetailFragment
import com.ksballetba.eyetonisher.ui.widgets.CategoryAdapterItemDecoration
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.utilities.getCateViewModel
import com.ksballetba.eyetonisher.utilities.getHomeViewModel
import com.ksballetba.eyetonisher.utilities.getTopicViewModel
import com.scwang.smartrefresh.layout.constant.RefreshState
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.fragment_category_detail.*
import org.jetbrains.anko.toast

class MoreActivity : AppCompatActivity() {

    val mFragmentList = mutableListOf<Fragment>()
    private lateinit var mFragment: CategoryDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimary)
        setContentView(R.layout.activity_more)
        setSupportActionBar(more_toolbar)
        supportActionBar?.title = ""
        initFragment()
    }

    private fun initFragment(){
        mFragment = CategoryDetailFragment()
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
