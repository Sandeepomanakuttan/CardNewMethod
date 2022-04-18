package com.example.sandeep.cartapp.response

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.network.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {

    private val retroService:ApiServices by lazy {

    RetroInstance.getRetofitClient().create(ApiServices::class.java)
    }

    var cartAllLiveData: MutableLiveData<CartResponse?> = MutableLiveData()

    fun getCartAllObserver(): MutableLiveData<CartResponse?> {
        return cartAllLiveData
    }


    suspend fun getCartList() : ArrayList<CartData>? {

        val body = CartBody()
        return withContext( Dispatchers.IO){ retroService.getCart(body).body()?.data}
    }


    suspend fun updateData(data: UpdateData): Boolean? {
         return withContext(Dispatchers.IO){retroService.updateCart(data).body()?.success}

    }

    suspend fun deleteCart(data: DeleteData) : Boolean?{
        return withContext(Dispatchers.IO){
            retroService.deleteCart(data).body()?.success
        }
    }
//    fun deleteCart1(data: DeleteData) {
//
//        val retroService = RetroInstance.getRetofitClient().create(ApiServices::class.java)
//        val apiService = retroService.  deleteCart(data)
//
//        apiService.enqueue(object : Callback<CartData> {
//            override fun onResponse(call: Call<CartData>, response: Response<CartData>) {
//                if (response.isSuccessful&& response.body()!=null){
//
//
//                    val result =cartAllLiveData.value?.data?.filter {
//                        it.pkProductId!=data.strProductId
//                    }
//
//                    result?.let {
//                        val cartResponse = CartResponse(true,"Sucess",result.size,
//                            result as ArrayList<CartData>?
//                        )
//                        cartAllLiveData.postValue(cartResponse)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<CartData>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })


//    }
}