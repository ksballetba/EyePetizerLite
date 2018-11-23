package com.ksballetba.eyetonisher.ui.fragments


import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*

import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.CateListBean
import com.ksballetba.eyetonisher.data.bean.HomeListBean
import com.ksballetba.eyetonisher.data.bean.TopicListBean
import com.ksballetba.eyetonisher.ui.acitvities.CategoryActivity
import com.ksballetba.eyetonisher.ui.acitvities.MoreActivity
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.acitvities.TopicActivity
import com.ksballetba.eyetonisher.ui.adapters.CateAdapter
import com.ksballetba.eyetonisher.ui.adapters.HomeAdapter
import com.ksballetba.eyetonisher.ui.adapters.TopicAdapter
import com.ksballetba.eyetonisher.ui.widgets.BannerItemDecoration
import com.ksballetba.eyetonisher.utilities.getCateViewModel
import com.ksballetba.eyetonisher.utilities.getHomeViewModel
import com.ksballetba.eyetonisher.utilities.getTopicViewModel
import com.ksballetba.eyetonisher.viewmodel.TopicViewModel
import kotlinx.android.synthetic.main.fragment_explorer.*

class ExplorerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        initBanner()
        initCategory()
        initReco()
    }

    private fun initBanner(){
        val topicList = mutableListOf<TopicListBean.Item>()
        val topicViewModel = getTopicViewModel(this)
        val  topicAdapter = TopicAdapter(topicList){
            navigateToTopic(topicList[it].data.id)
        }
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        topic_banner.layoutManager = layoutManager
        topic_banner.addItemDecoration(BannerItemDecoration())
        topic_banner.adapter = topicAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(topic_banner)
        topicViewModel.fetchData().observe(viewLifecycleOwner, Observer {
            topicList.addAll(it.subList(0,5))
            topicAdapter.update(it.subList(0,5))
        })
        alltopic_action.setOnClickListener {
            navigateToMore("topiclist")
        }
    }

    private fun initCategory(){
        var cateList = mutableListOf<CateListBean.Item>()
        val cateViewModel = getCateViewModel(this)
        val cateAdapter = CateAdapter(cateList){
            navigateToCategoryDetail(cateList[it].data.id,cateList[it].data.title)
        }

        val layoutManager = GridLayoutManager(context,2)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        category_rec.layoutManager = layoutManager
        category_rec.adapter = cateAdapter
        cateViewModel.fetchData().observe(viewLifecycleOwner, Observer {
            cateList = it.filter {
                it.data.id>1
            }.toMutableList()
            cateAdapter.update(cateList)
        })
        allcategory_action.setOnClickListener {
            navigateToMore("catelist")
        }
    }

    private fun initReco(){
        var recoList = mutableListOf<HomeListBean.Item>()
        val recoViewModel = getHomeViewModel(this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        reco_rec.layoutManager = layoutManager
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener{
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(recoList[idx].data.header.id,recoList[idx].data.content.data.playUrl,recoList[idx].data.content.data.title,recoList[idx].data.content.data.cover.detail)
            }
            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        val recoAdapter = HomeAdapter(recoList,itemOnClickListener)
        reco_rec.adapter = recoAdapter
        reco_rec.addItemDecoration(BannerItemDecoration())
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(reco_rec)
        recoViewModel.fetchRecoData().observe(viewLifecycleOwner, Observer {
            recoList = it.subList(0,5)
            recoAdapter.update(recoList)
        })
        allreco_action.setOnClickListener {
            navigateToMore("recolist")
        }
    }

    private fun navigateToCategoryDetail(id:Int,title:String){
        val intent = Intent(activity,CategoryActivity::class.java)
        intent.putExtra("category_id",id)
        intent.putExtra("category_title",title)
        startActivity(intent)
    }

    private fun navigateToPlayDetail(videoId:Int,videoUrl:String,videoTitle:String,videoThumb:String){
        val intent = Intent(activity,PlayDetailActivity::class.java)
        intent.putExtra("video_url",videoUrl)
        intent.putExtra("video_id",videoId)
        intent.putExtra("video_title",videoTitle)
        intent.putExtra("video_thumb",videoThumb)
        startActivity(intent)
    }

    private fun navigateToMore(initType:String){
        val intent = Intent(activity,MoreActivity::class.java)
        intent.putExtra("init_type",initType)
        startActivity(intent)
    }

    private fun navigateToTopic(id:Int){
        val intent = Intent(activity,TopicActivity::class.java)
        intent.putExtra("topic_id",id)
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
}
