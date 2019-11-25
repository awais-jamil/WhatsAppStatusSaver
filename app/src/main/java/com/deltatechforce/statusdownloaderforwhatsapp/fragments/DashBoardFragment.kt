package com.deltatechforce.statusdownloaderforwhatsapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager

import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseFragment
import com.deltatechforce.statusdownloaderforwhatsapp.viewmodels.DashboardViewModel
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 */
class DashBoardFragment : BaseFragment() {

    val dashboardViewModel by activityViewModels<DashboardViewModel>()

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var currentTab: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Images"))
        tabLayout.addTab(tabLayout.newTab().setText("Videos"))
//        tabLayout.addTab(tabLayout.newTab().setText("Saved"))

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)


        val adapter = MyAdapter(context!!, childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        currentTab = "Images"
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                viewPager.currentItem = tab.position
                when(tab.position){
                    0->
                        currentTab = "Images"
                    1->
                        currentTab = "Videos"
//                    2->
//                        currentTab = "Saved"
                }

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        return view
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        activity?.runOnUiThread {

            dashboardViewModel.getStatus()
        }
    }

    class MyAdapter(
        private val myContext: Context,
        fm: FragmentManager,
        internal var totalTabs: Int
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        // this is for fragment tabs
        override fun getItem(position: Int): Fragment {
            if(position == 0) { //Community
                return PhotoFragment()
            }
            else  { //Private
                return VideoFragment()
            }
//            else { //Sent
//                return  VideoFragment()
//            }
        }
        // this counts total number of tabs
        override fun getCount(): Int {
            return totalTabs
        }
    }
}
