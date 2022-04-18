package com.example.sandeep.cartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.sandeep.cartapp.databinding.ActivityMainBinding
import com.example.sandeep.cartapp.view.product.allProduct.AllProduct
import com.example.sandeep.cartapp.view.product.adaptor.PageControlAdaptor
import com.example.sandeep.cartapp.view.product.cartItem.CartItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private var tabLayout: TabLayout?=null
    private var viewPager: ViewPager2?=null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        tabLayout=binding.tabLayout
        viewPager=binding.pager

        val adaptor = PageControlAdaptor(this)
        adaptor.addFragment(AllProduct(), "ALL")
        adaptor.addFragment(CartItem(), "Cart")

        viewPager!!.adapter = adaptor
        viewPager!!.currentItem = 0
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = adaptor.getTabTitle(position)
        }.attach()
    }
}