package com.ksballetba.eyetonisher.ui.acitvities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_category)
        initToolbar()
        initFragments()
    }

    private fun initToolbar(){
        setSupportActionBar(category_toolbar)
        supportActionBar?.title = ""
        category_title.text = intent.getStringExtra("category_title")
        category_coll_title.text = intent.getStringExtra("category_title")
        when(intent.getStringExtra("category_title")){
            "#时尚"->{
                category_slogan.text = "优雅地行走在潮流尖端"
            }
            "#运动"->{
                category_slogan.text = "冲浪、滑板、跑酷、骑行，生命停不下来"
            }
            "#创意"->{
                category_slogan.text = "技术与审美结合，探索视觉的无限可能"
            }
            "#广告"->{
                category_slogan.text = "为广告人的精彩创意点赞"
            }
            "#音乐"->{
                category_slogan.text = "汇聚全球最新、最优质的音乐视频"
            }
            "#记录"->{
                category_slogan.text = "告诉他们为什么与众不同"
            }
            "#开胃"->{
                category_slogan.text = "眼球和味蕾，一个都不放过"
            }
            "#游戏"->{
                category_slogan.text = "欢迎来到惊险刺激的新世界"
            }
            "#萌宠"->{
                category_slogan.text = "来自汪星，喵星，蠢萌星的你"
            }
            "#动画"->{
                category_slogan.text = "有趣的人永远不缺童心"
            }
            "#科技"->{
                category_slogan.text = "每天获得新知识"
            }
            "#剧情"->{
                category_slogan.text = "用一个好故事，描绘生活的不可思议"
            }
            "#搞笑"->{
                category_slogan.text = "哈哈哈哈哈哈哈哈哈"
            }
            "#影视"->{
                category_slogan.text = "电影、剧集、戏剧抢先看"
            }
            "#综艺"->{
                category_slogan.text = "全球网红在表演什么"
            }
            "#生活"->{
                category_slogan.text = "匠心、健康、生活感悟"
            }
            "#旅行"->{
                category_slogan.text = "发现世界的奇妙和辽阔"
            }
        }
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
        category_viewpager.offscreenPageLimit = 4
        category_tablayout.setupWithViewPager(category_viewpager)
        category_tablayout.getTabAt(0)?.text = "热门"
        category_tablayout.getTabAt(1)?.text = "全部"
        category_tablayout.getTabAt(2)?.text = "专辑"
        category_tablayout.getTabAt(3)?.text = "作者"
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
