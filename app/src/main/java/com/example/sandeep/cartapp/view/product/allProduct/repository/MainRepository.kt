package com.example.sandeep.cartapp.view.product.allProduct.repository


import android.util.Log
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.di.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import com.example.sandeep.cartapp.view.product.utils.APiException
import com.example.sandeep.cartapp.view.product.utils.SafeApiRequest
import kotlinx.coroutines.*


class MainRepository : SafeApiRequest() {

    private val retroService: ApiServices by lazy { RetroInstance.getRetrofitClient().create(ApiServices::class.java) }




    private suspend fun addCart(data: AddCart): AddCrtResponse{
        return withContext(Dispatchers.IO){apiRequest {
            retroService.addCart(data)}
        }
    }


    private suspend fun updateData(data: UpdateData): UpdateResponse {
        return withContext(Dispatchers.IO){apiRequest {retroService.updateCart(data)}}

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

            try {
                val response = updateData(upData)

                response.success?.let {
                    return it
                }
            }catch (e: APiException){
                Log.e("Update Main",e.message!!)
            }

        }else{
            try {
                val res = addCart(data)

                res.success?.let { its->
                    if (its){
                        Log.e("Add Cart","Successfully added")
                        return true
                    }else{
                        return false
                    }
                }

            }catch (e: APiException){
                Log.e("Add cart",e.message!!)
            }

        }



        return false
    }

}


