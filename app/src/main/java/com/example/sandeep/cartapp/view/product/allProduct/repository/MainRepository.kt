package com.example.sandeep.cartapp.view.product.allProduct.repository


import android.util.Log
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.network.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import kotlinx.coroutines.*


class MainRepository {

    private val retroService: ApiServices by lazy { RetroInstance.getRetofitClient().create(ApiServices::class.java) }




    suspend fun addCart(data: AddCart): Boolean?{
        return withContext(Dispatchers.IO){
            retroService.addCart(data).body()?.success
        }
    }


    private suspend fun updateData(data: UpdateData): Boolean? {
        return withContext(Dispatchers.IO){retroService.updateCart(data).body()?.success}

    }

    suspend fun checkCart(data: AddCart) : Boolean {
        val body = CartBody()
        val returnData:ArrayList<CartData>? =
            withContext(Dispatchers.IO){ retroService.getCart(body).body()?.data}
        val result = returnData?.filter {
            it.pkProductId==data.strProductId
        }

        Log.e("printing data",result.toString())
        if (result?.size!=0){
            val cartData = result?.get(0)
            val upData=UpdateData()
            upData.intQuantity=cartData?.intQuantity?.plus(1)
            upData.strLoginUserId=data.strLoginUserId
            upData.strProductId=data.strProductId
            upData.strStoreId=data.strStoreId

            if(updateData(upData)==true){
                return true
            }
        }


        result.let {
            if (addCart(data) == true){
                return true
            }
        }

        return false
    }

}


