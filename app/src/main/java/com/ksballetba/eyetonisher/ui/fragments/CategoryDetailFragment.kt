package com.ksballetba.eyetonisher.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.data.bean.*
import com.ksballetba.eyetonisher.network.Status
import com.ksballetba.eyetonisher.ui.acitvities.CategoryActivity
import com.ksballetba.eyetonisher.ui.acitvities.PlayDetailActivity
import com.ksballetba.eyetonisher.ui.adapters.*
import com.ksballetba.eyetonisher.ui.widgets.CategoryAdapterItemDecoration
import com.ksballetba.eyetonisher.ui.widgets.MarginDividerItemDecoration
import com.ksballetba.eyetonisher.utilities.getCateViewModel
import com.ksballetba.eyetonisher.utilities.getCategoryDeatilViewModel
import com.ksballetba.eyetonisher.utilities.getHomeViewModel
import com.ksballetba.eyetonisher.utilities.getTopicViewModel
import com.ksballetba.eyetonisher.viewmodel.CategoryDetailViewModel
import com.ksballetba.eyetonisher.viewmodel.TopicViewModel
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
    lateinit var mTopicViewModel: TopicViewModel
    var mHotVideoList = mutableListOf<CategoryHomeListBean.Item>()
    var mAllVideoList = mutableListOf<RankListBean.Item>()
    var mPlayList = mutableListOf<CategotyPlaylistBean.Item>()
    var mProviderList = mutableListOf<CategotyPlaylistBean.Item>()
    var mTopicList = mutableListOf<TopicListBean.Item>()
    lateinit var mHotAdapter: CategoryHotAdapter
    lateinit var mAllAdapter: RankAdapter
    lateinit var mPlaylistAdapter: CategoryPlaylistAdapter
    lateinit var mProvidersAdapter: CategoryPlaylistAdapter
    lateinit var mTopicAdapter: TopicAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is CategoryActivity) {
            val act = activity as CategoryActivity
        }
        val id = activity?.intent?.getIntExtra("category_id", 0)
        val type = (arguments as Bundle).getString("init_type", "")
        initRefresh()
        when (type) {
            "hotvideolist" -> {
                initHotRec(id!!)
            }
            "allvideolist" -> {
                initAllRec(id!!)
            }
            "playlist" -> {
                initPlaylistRec(id!!)
            }
            "providerlist" -> {
                initProviderlistRec(id!!)
            }
            "topiclist" -> {
                initTopicRec()
            }
            "catelist" -> {
                initCategoryRec()
            }
            "recolist" -> {
                initRecoRec()
            }
        }
    }

    private fun initHotRec(id: Int) {
        category_detail_refresh.setEnableLoadMore(false)
        mViewModel = getCategoryDeatilViewModel(this, id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mHotVideoList[idx].data.id, mHotVideoList[idx].data.playUrl, mHotVideoList[idx].data.title, mHotVideoList[idx].data.cover.detail)
            }

            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        mHotAdapter = CategoryHotAdapter(mHotVideoList, itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(mHotAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        category_detail_refresh.autoRefresh()
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
        category_detail_refresh.setOnRefreshListener {
            mViewModel.fetchHotVideoList().observe(viewLifecycleOwner, Observer {
                mHotVideoList = it.filter {
                    it.type == "video"
                }.toMutableList()
                mHotAdapter.update(mHotVideoList)
                val topicBg = activity?.findViewById<ImageView>(R.id.category_bg)
                val options = RequestOptions().placeholder(R.color.icons).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                Glide.with(context).load(mHotVideoList[3].data.cover.detail).apply(options).transition(DrawableTransitionOptions.withCrossFade(500)).into(topicBg)
            })
        }
    }

    private fun initAllRec(id: Int) {
        mViewModel = getCategoryDeatilViewModel(this, id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(mAllVideoList[idx].data.id, mAllVideoList[idx].data.playUrl, mAllVideoList[idx].data.title, mAllVideoList[idx].data.cover.detail)
            }

            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        mAllAdapter = RankAdapter(mAllVideoList, itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(mAllAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        category_detail_refresh.autoRefresh()
        mViewModel.fetchLoadDataStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
//                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
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

    private fun initPlaylistRec(id: Int) {
        mViewModel = getCategoryDeatilViewModel(this, id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {

            }

            override fun onActionClick(idx: Int) {

            }
        }
        mPlaylistAdapter = CategoryPlaylistAdapter(mPlayList, itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(mPlaylistAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        category_detail_refresh.autoRefresh()
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

        category_detail_refresh.setOnRefreshListener {
            mViewModel.fetchPlaylistInitial().observe(viewLifecycleOwner, Observer {
                mPlaylistAdapter.update(it)
                mPlayList = it.toMutableList()
            })
        }
        category_detail_refresh.setOnLoadMoreListener {
            mViewModel.fetchPlaylistAfter().observe(viewLifecycleOwner, Observer {
                mPlaylistAdapter.add(it)
                mPlayList.addAll(it)
                category_detail_refresh.finishLoadMore()
            })
        }
    }

    private fun initProviderlistRec(id: Int) {
        mViewModel = getCategoryDeatilViewModel(this, id)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {

            }

            override fun onActionClick(idx: Int) {

            }
        }
        mProvidersAdapter = CategoryPlaylistAdapter(mProviderList, itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(mProvidersAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        category_detail_refresh.autoRefresh()
        mViewModel.fetchLoadDataStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
//                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        category_detail_refresh.setOnRefreshListener {
            mViewModel.fetchProviderListAfter().observe(viewLifecycleOwner, Observer {
                mProvidersAdapter.update(it)
                mProviderList = it.toMutableList()
            })
        }
        category_detail_refresh.setOnLoadMoreListener {
            mViewModel.fetchProviderListAfter().observe(viewLifecycleOwner, Observer {
                mProvidersAdapter.add(it)
                mProviderList.addAll(it)
                category_detail_refresh.finishLoadMore()
            })
        }
    }

    private fun initTopicRec() {
        mTopicViewModel = getTopicViewModel(this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        mTopicAdapter = TopicAdapter(mTopicList) {

        }
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(mTopicAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        mTopicViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
//                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })

        mTopicViewModel.fetchData().observe(viewLifecycleOwner, Observer {
            mTopicAdapter.update(it)
            mTopicList = it.toMutableList()
        })
        category_detail_refresh.setOnRefreshListener {
            mTopicViewModel.fetchData().observe(viewLifecycleOwner, Observer {
                mTopicAdapter.update(it)
                mTopicList = it.toMutableList()
            })
        }
        category_detail_refresh.setOnLoadMoreListener {
            mTopicViewModel.fetchDataAfter().observe(viewLifecycleOwner, Observer {
                mTopicAdapter.add(it)
                mTopicList.addAll(it)
                category_detail_refresh.finishLoadMore()
            })
        }
    }

    private fun initCategoryRec() {
        category_detail_refresh.setEnableLoadMore(false)
        category_detail_refresh.setEnableRefresh(false)
        val cateList = mutableListOf<CateListBean.Item>()
        val cateViewModel = getCateViewModel(this)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        val cateAdapter = CateAdapter(cateList) {
            navigateToCategoryDetail(cateList[it].data.id, cateList[it].data.title)
        }
        category_detail_rec.addItemDecoration(CategoryAdapterItemDecoration())
        category_detail_rec.layoutManager = layoutManager
        val animationAdapter = ScaleInAnimationAdapter(cateAdapter)
        animationAdapter.setFirstOnly(false)
        category_detail_rec.adapter = animationAdapter
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        cateViewModel.fetchData().observe(viewLifecycleOwner, Observer {
            cateList.addAll(it)
            cateAdapter.update(it)
        })
    }

    private fun initRecoRec() {
        val recoList = mutableListOf<HomeListBean.Item>()
        val recoViewModel = getHomeViewModel(this)
        val layoutManager = LinearLayoutManager(context!!)
        layoutManager.orientation = RecyclerView.VERTICAL
        val itemOnClickListener = object : HomeAdapter.ItemOnClickListener {
            override fun onDetailClick(idx: Int) {
                navigateToPlayDetail(recoList[idx].data.header.id, recoList[idx].data.content.data.playUrl, recoList[idx].data.content.data.title, recoList[idx].data.content.data.cover.detail)
            }

            override fun onActionClick(idx: Int) {
                showPopMenu(layoutManager.findViewByPosition(idx)!!.findViewById(R.id.video_item_action))
            }
        }
        val recoAdapter = HomeAdapter(recoList, itemOnClickListener)
        category_detail_rec.layoutManager = layoutManager
        category_detail_rec.addItemDecoration(MarginDividerItemDecoration(0f, 0f, context!!))
        val animationAdapter = ScaleInAnimationAdapter(recoAdapter)
        category_detail_rec.adapter = animationAdapter
        animationAdapter.setFirstOnly(false)
        category_detail_rec.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        recoViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
//                    category_detail_refresh.autoRefresh()
                }
                Status.SUCCESS -> {
                    category_detail_refresh.finishRefresh()
                }
                Status.FAILED -> {
                    activity?.toast("网络加载失败")
                }
            }
        })
        recoViewModel.fetchRecoData().observe(viewLifecycleOwner, Observer {
            recoList.addAll(it)
            recoAdapter.update(it)
        })
        category_detail_refresh.setOnRefreshListener {
            recoViewModel.fetchRecoData().observe(viewLifecycleOwner, Observer {
                recoList.addAll(it)
                recoAdapter.update(it)
            })
        }
        category_detail_refresh.setOnLoadMoreListener {
            recoViewModel.fetchRecoDataAfter().observe(viewLifecycleOwner, Observer {
                recoList.addAll(it)
                recoAdapter.add(it)
                category_detail_refresh.finishLoadMore()
            })
        }
    }

    private fun navigateToCategoryDetail(id: Int, title: String) {
        val intent = Intent(activity, CategoryActivity::class.java)
        intent.putExtra("category_id", id)
        intent.putExtra("category_title", title)
        startActivity(intent)
    }


    private fun navigateToPlayDetail(videoId: Int, videoUrl: String, videoTitle: String, videoThumb: String) {
        val intent = Intent(activity, PlayDetailActivity::class.java)
        intent.putExtra("video_url", videoUrl)
        intent.putExtra("video_id", videoId)
        intent.putExtra("video_title", videoTitle)
        intent.putExtra("video_thumb", videoThumb)
        startActivity(intent)
    }

    private fun showPopMenu(view: View) {
        val popupMenu = PopupMenu(context, view, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_fav -> {

                }
                R.id.action_download -> {

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
