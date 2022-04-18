package com.example.sandeep.cartapp.view.product.allProduct.repository


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.network.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class MainRepository {

    var productAllLiveData: MutableLiveData<ProductResponse?> = MutableLiveData()
    var cartAllLiveData: MutableLiveData<CartResponse?> = MutableLiveData()
    var cart: MutableLiveData<CartResponse?> = MutableLiveData()
    private val retroService: ApiServices by lazy { RetroInstance.getRetofitClient().create(ApiServices::class.java) }


    var viewLifecycleOwner: LifecycleOwner? = null




    fun getProductList() {

        val body = ProductBody()

        val apiService = retroService.getProduct(body)

        apiService.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {

                    productAllLiveData.postValue(response.body())
                } else {
                    productAllLiveData.postValue(null)

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("status..", t.toString())

            }
        })
    }

    suspend fun addCart(data: AddCart): Boolean?{
        return withContext(Dispatchers.IO){
            retroService.addCart(data).body()?.success
        }
    }


    suspend fun updateData(data: UpdateData): Boolean? {
        return withContext(Dispatchers.IO){retroService.updateCart(data).body()?.success}

    }

    suspend fun checkCart(data: AddCart) : Boolean?{
        val body = CartBody()
        var returnData:ArrayList<CartData>?=null
        returnData =withContext(Dispatchers.IO){ retroService.getCart(body).body()?.data}
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

//    fun addCart(data : AddCart) {
//
//        try {
//
//            val service = retroService.addCart(data)
//
//            service.enqueue(object : Callback<AddCrtResponse> {
//                override fun onResponse(
//                    call: Call<AddCrtResponse>,
//                    response: Response<AddCrtResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        if (response.body() != null) {
//                            Log.e("addCart", response.body().toString())
//                            //getCartList()
//                        }
//
//                    } else {
//                        Log.e("res", "failed")
//
//                    }
//
//
//                }
//
//                override fun onFailure(call: Call<AddCrtResponse>, t: Throwable) {
//                    Log.e("Error", t.message.toString())
//                }
//            })
//
//        } catch (e: Exception) {
//            Log.e("Error", e.message.toString())
//        }
//    }


//    fun checkCart(adddata: AddCart) {
//
//        var flags: Int? = null
//        val body = CartBody()
//        val apiService = retroService.getCart(body)
//
//
//        apiService.enqueue(object : Callback<CartResponse> {
//            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
//
//
//                if (response.isSuccessful && response.body() != null) {
//
//                    cart.postValue(response.body())
//                    if (cart.value!=null) {
//
//                        var up: UpdateData=UpdateData()
//
//                        this@MainRepository.cart.observe(viewLifecycleOwner!!) {
//                            val resData = it?.data
//
//                            for (list in resData!!) {
//
//
//                                flags = 0
//                                if (list.pkProductId == adddata.strProductId) {
//                                    val count = list.intQuantity?.plus(1)!!
//
//                                        up.intQuantity=count
//                                        up.strLoginUserId=adddata.strLoginUserId
//                                        up.strProductId=adddata.strProductId
//                                        up.strStoreId =adddata.strStoreId
//
//                                    flags = 1
//                                    break
//                                }
//                            }
//
//                        }
//
//                        if (flags == 0) {
//                            addCart(adddata)
//                        } else {
//                            updateData(data = up)
//                            return
//                        }
//                    }else{
//                        addCart(adddata)
//                        Log.e("heloooooo","heloooo")
//                    }
//                }else{
//                    response.errorBody()
//                }
//
//            }
//
//            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//    }
//    fun getCartList(){
//
//        val body = CartBody()
//        val retroService = RetroInstance.getRetofitClient().create(ApiServices::class.java)
//        val apiService = retroService.getCart(body)
//
//        apiService.enqueue(object : Callback<CartResponse> {
//            override fun onResponse(
//                call: Call<CartResponse>,
//                response: Response<CartResponse>
//            ) {
//                if (response.isSuccessful) {
//
//                    Log.e("get Cart",response.body().toString())
//
//                    cartAllLiveData.postValue(response.body())
//
//
//                }else{
//                     cartAllLiveData.postValue(null)
//
//                }
//            }
//
//            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
//                Log.e("Main repository..",t.toString())
//
//            }
//        })
//    }



}


