package com.ksballetba.eyetonisher.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.VideoInfoBean
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.ui.widgets.HeaderItemDecoration
import com.ksballetba.eyetonisher.utilities.createFavVideo
import com.ksballetba.eyetonisher.utilities.getFavVideoViewModel
import com.ksballetba.eyetonisher.utilities.getHomeViewModel
import com.ksballetba.eyetonisher.viewmodel.FavVideoViewModel
import com.ksballetba.eyetonisher.viewmodel.HomeViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var mViewModel: HomeViewModel
    private lateinit var mDBViewModel: FavVideoViewModel
    var mHomeList = mutableListOf<HomeListBean.Item>()
    private lateinit var mHomeAdapter: HomeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRefresh()
        initRec()
    }

    private fun initRec() {
        mViewModel = getHomeViewModel(this)
        mDBViewModel = getFavVideoViewModel(this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        home_rec.layoutManager = layoutManager
        val itemOnClickListener = object :HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mHomeList[idx].data.header.id,mHomeList[idx].data.content.data.playUrl,mHomeList[idx].data.content.data.title,mHomeList[idx].data.content.data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action),mHomeList[idx].data.content.data)
            }
        }
        mHomeAdapter = HomeAdapter(mHomeList,itemOnClickListener)
        val animationAdapter = ScaleInAnimationAdapter(mHomeAdapter)
        animationAdapter.setFirstOnly(false)
        home_rec.adapter = animationAdapter
        home_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        val decorationCallback = object :HeaderItemDecoration.DecorationCallback{
            override fun isGroupFirst(position: Int) = mHomeList[position].isFirst!!
            override fun getGroupFirstTitle(position: Int) = mHomeList[position].title!!
        }
        val headerItemDecoration = HeaderItemDecoration(decorationCallback)
        home_rec.addItemDecoration(headerItemDecoration)
        home_rec.addItemDecoration(MarginDividerItemDecoration(0f,0f,context!!))
        mViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    home_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    home_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        home_refresh.autoRefresh()
        home_refresh.setOnRefreshListener {
            loadInitial()
        }
        home_refresh.setOnLoadMoreListener {
            loadAfter()
        }
    }

    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(activity,PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View,video:VideoInfoBean){
        val popupMenu = PopupMenu(context,view,Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav->{
                    mDBViewModel.insertVideo(createFavVideo(video))
                }
                R.id.action_download->{

                }
            }
            true
        }
    }

    private fun loadInitial() {
        mViewModel.fetchHomeData().observe(viewLifecycleOwner, Observer {
            val titledList = generateTitleforList(it)
            val updateList = titledList.filter {
                it.type != "textCard" && it.data.content.data.title.isNotBlank()
            }
            mHomeAdapter.update(updateList)
            mHomeList = updateList.toMutableList()
        })
    }

    private fun loadAfter() {
        mViewModel.fetchHomeDataAfter(mViewModel.getNextPageUrl().value!!).observe(viewLifecycleOwner, Observer {
            val titledList = generateTitleforList(it)
            val addList = titledList.filter {
                it.type != "textCard" && it.data.content.data.title.isNotBlank()
            }
            mHomeAdapter.add(addList)
            mHomeList.addAll(addList)
            home_refresh.finishLoadMore()
        })
    }

    private fun generateTitleforList(list:List<HomeListBean.Item>):List<HomeListBean.Item>{
       for(i in 1 until list.size){
           if(list[i-1].type == "textCard"){
               list[i].isFirst = true
               list[i].title = list[i-1].data.text
           } else{
               list[i].isFirst = false
               list[i].title = "剧本暂无"
           }
       }
        return list
    }


    private fun initRefresh() {
        home_refresh.setEnableRefresh(true)
        home_refresh.setEnableLoadMore(true)
        home_refresh.setEnableOverScrollBounce(true)//是否启用越界回弹
        home_refresh.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        home_refresh.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        home_refresh.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        home_refresh.setEnableLoadMoreWhenContentNotFull(true)
    }


}
