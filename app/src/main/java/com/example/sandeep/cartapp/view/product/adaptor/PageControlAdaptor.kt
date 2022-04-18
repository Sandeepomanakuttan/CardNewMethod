package com.example.sandeep.cartapp.view.product.adaptor

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sandeep.cartapp.MainActivity

class PageControlAdaptor(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()

    fun getTabTitle(position : Int): String{
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

}