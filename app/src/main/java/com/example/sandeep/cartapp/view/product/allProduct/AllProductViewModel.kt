package com.example.sandeep.cartapp.view.product.allProduct

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.di.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import com.example.sandeep.cartapp.view.product.allProduct.repository.MainRepository

class AllProductViewModel(val repository : MainRepository) : ViewModel() {


    private val retroService: ApiServices by lazy {
        RetroInstance.getRetrofitClient().create(
            ApiServices::class.java
        )
    }

    val getData =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            PagingSources(retroService)
        }).flow.cachedIn(viewModelScope)


    suspend fun addCart(data : AddCart){
        val status = repository.checkCart(data)

        if (status){
            Log.e("Cart Update","Successfully Added")
        }else{
            Log.e("Cart Update","Fail Added")
        }
    }





}


