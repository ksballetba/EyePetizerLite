package com.ksballetba.eyetonisher.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.CategoryHomeListBean
import com.ksballetba.eyetonisher.data.bean.CategotyPlaylistBean
import com.ksballetba.eyetonisher.data.bean.CategotyProvidersBean
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.ui.acitvities.CategoryActivity
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.adapters.CategoryHotAdapter
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.RankAdapter
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.utilities.getCategoryDeatilViewModel
import com.ksballetba.eyetonisher.viewmodel.CategoryDetailViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_category_detail.*
import kotlinx.android.synthetic.main.fragment_rank.*
import org.jetbrains.anko.toast

//
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 *
 */
class CategoryDetailFragment : Fragment() {

    lateinit var mViewModel: CategoryDetailViewModel
    var mHotVideoList = mutableListOf<CategoryHomeListBean.Item>()
    var mAllVideoList = mutableListOf<RankListBean.Item>()
    var mPlayList = mutableListOf<CategotyPlaylistBean.Item>()
    var mProviderList = mutableListOf<CategotyProvidersBean.Item>()
    lateinit var mHotAdapter:CategoryHotAdapter
    lateinit var mAllAdapter:RankAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = activity as CategoryActivity
        val id = act.intent.getIntExtra("category_id",0)
        val type =  (arguments as Bundle).getString("init_type","")
        initRefresh()
        when(type){
            "hotvideolist"->{
                initHotRec(id)
            }
            "allvideolist"->{
                initAllRec(id)
            }
            "playlist"->{

            }
            "providerlist"->{

            }
        }
    }

    private fun initHotRec(id:Int){
        category_detail_refresh.setEnableLoadMore(false)
        mViewModel = getCategoryDeatilViewModel(this,id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mHotVideoList[idx].data.id,mHotVideoList[idx].data.playUrl,mHotVideoList[idx].data.title,mHotVideoList[idx].data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        mHotAdapter = CategoryHotAdapter(mHotVideoList,itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f,0f,context!!))
        val animationAdapter = ScaleInAnimationAdapter(mHotAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        mViewModel.fetchLoadDataStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        mViewModel.fetchHotVideoList().observe(viewLifecycleOwner, Observer {
            mHotVideoList = it.filter {
                it.type == "video"
            }.toMutableList()
            mHotAdapter.update(mHotVideoList)
        })
        category_detail_refresh.setOnRefreshListener {
            mViewModel.fetchHotVideoList().observe(viewLifecycleOwner, Observer {
                mHotVideoList = it.filter {
                    it.type == "video"
                }.toMutableList()
                mHotAdapter.update(mHotVideoList)
            })
        }
    }

    private fun initAllRec(id:Int){
        mViewModel = getCategoryDeatilViewModel(this,id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mAllVideoList[idx].data.id,mAllVideoList[idx].data.playUrl,mAllVideoList[idx].data.title,mAllVideoList[idx].data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        mAllAdapter = RankAdapter(mAllVideoList,itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f,0f,context!!))
        val animationAdapter = ScaleInAnimationAdapter(mAllAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        mViewModel.fetchLoadDataStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        mViewModel.fetchAllVideoListInitial().observe(viewLifecycleOwner, Observer {
            mAllAdapter.update(it)
            mAllVideoList = it.toMutableList()
        })
        category_detail_refresh.setOnRefreshListener {
            mViewModel.fetchAllVideoListInitial().observe(viewLifecycleOwner, Observer {
                mAllAdapter.update(it)
                mAllVideoList = it.toMutableList()
            })
        }
        category_detail_refresh.setOnLoadMoreListener {
            mViewModel.fetchAllVideoListAfter().observe(viewLifecycleOwner, Observer {
                mAllAdapter.add(it)
                mAllVideoList.addAll(it)
                category_detail_refresh.finishLoadMore()
            })
        }
    }



    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(activity, PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View){
        val popupMenu = PopupMenu(context,view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{

                }
                R.id.action_download->{

                }
            }
            true
        }
    }

    private fun initRefresh() {
        category_detail_refresh.setEnableRefresh(true)
        category_detail_refresh.setEnableLoadMore(true)
        category_detail_refresh.setEnableOverScrollBounce(true)//是否启用越界回弹
        category_detail_refresh.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        category_detail_refresh.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        category_detail_refresh.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        category_detail_refresh.setEnableLoadMoreWhenContentNotFull(true)
    }
}
