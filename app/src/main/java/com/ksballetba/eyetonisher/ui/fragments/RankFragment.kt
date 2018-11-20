
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
import com.ksballetba.eyetonisher.data.bean.RankListBean
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.RankAdapter
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.utilities.getRankViewModel
import com.ksballetba.eyetonisher.viewmodel.RankViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_rank.*
import org.jetbrains.anko.toast

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RankFragment: Fragment() {

    lateinit var mViewModel:RankViewModel
    var mRankList = mutableListOf<RankListBean.Item>()
    private lateinit var mRankAdapter: RankAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_rank, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRefresh()
        initRec()
    }

    private fun initRec() {
        val strategy =  (arguments as Bundle).getString("strategy","")
        mViewModel = getRankViewModel(this,strategy)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mRankList[idx].data.id,mRankList[idx].data.playUrl,mRankList[idx].data.title,mRankList[idx].data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        mRankAdapter = RankAdapter(mRankList,itemOnClickListener)
        val rankRec = view?.findViewById<RecyclerView>(R.id.rank_rec)
        rankRec?.layoutManager = layoutManager
        rank_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        rank_rec.addItemDecoration(MarginDividerItemDecoration(0f,0f,context!!))
        val animationAdapter = ScaleInAnimationAdapter(mRankAdapter)
        animationAdapter.setFirstOnly(false)
        rankRec?.adapter = animationAdapter
        mViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
//                    rank_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    rank_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        rank_refresh.autoRefresh()
        rank_refresh.setOnRefreshListener {
            loadInitial()
        }
        rank_refresh.setOnLoadMoreListener {
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

    private fun loadInitial() {
        mViewModel.fetchData().observe(viewLifecycleOwner, Observer {
            mRankAdapter.update(it)
            mRankList = it.toMutableList()
        })
    }

    private fun loadAfter() {
        mViewModel.fetchDataAfter().observe(viewLifecycleOwner, Observer {
            mRankAdapter.add(it)
            mRankList.addAll(it)
            rank_refresh.finishLoadMore()
        })
    }

    private fun initRefresh() {
        rank_refresh.setEnableRefresh(true)
        rank_refresh.setEnableLoadMore(true)
        rank_refresh.setEnableOverScrollBounce(true)//是否启用越界回弹
        rank_refresh.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        rank_refresh.setEnableHeaderTranslationContent(true)//是否下拉Header的时候向下平移列表或者内容
        rank_refresh.setEnableFooterTranslationContent(true)//是否上拉Footer的时候向上平移列表或者内容
        rank_refresh.setEnableLoadMoreWhenContentNotFull(true)
    }

}
