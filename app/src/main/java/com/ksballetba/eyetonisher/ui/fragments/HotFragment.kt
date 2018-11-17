package com.ksballetba.eyetonisher.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout

import com.ksballetba.eyetonisher.R
import com.ksballetba.eyetonisher.ui.acitvities.MainActivity
import com.ksballetba.eyetonisher.utilities.setTabWidth
import kotlinx.android.synthetic.main.fragment_hot.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HotFragment : Fragment() {

    val fragmentList = mutableListOf<Fragment>()
    lateinit var mWeeklyRankFragment: RankFragment
    lateinit var mMonthlyRankFragment: RankFragment
    lateinit var mAllRankFragment: RankFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragments()
    }

    private fun initFragments(){
        mWeeklyRankFragment = RankFragment()
        mMonthlyRankFragment = RankFragment()
        mAllRankFragment = RankFragment()
        val weekBundle = Bundle()
        weekBundle.putString("strategy","weekly")
        mWeeklyRankFragment.arguments = weekBundle
        val monthBundle = Bundle()
        monthBundle.putString("strategy","monthly")
        mMonthlyRankFragment.arguments = monthBundle
        val allBundle = Bundle()
        allBundle.putString("strategy","historical")
        mAllRankFragment.arguments = allBundle
        val rankTabLayout = (activity as MainActivity).findViewById<TabLayout>(R.id.rank_tablayout)
        setTabWidth(rankTabLayout,30f,30f)
        fragmentList.add(mWeeklyRankFragment)
        fragmentList.add(mMonthlyRankFragment)
        fragmentList.add(mAllRankFragment)
        rank_viewpager.adapter = ViewPagerAdapter(fragmentList,childFragmentManager)
        rank_viewpager.offscreenPageLimit = 3
        rankTabLayout.setupWithViewPager(rank_viewpager)
        rankTabLayout.getTabAt(0)?.text = "周排行"
        rankTabLayout.getTabAt(1)?.text = "月排行"
        rankTabLayout.getTabAt(2)?.text = "总排行"
    }

}

class ViewPagerAdapter(var mList : List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

}