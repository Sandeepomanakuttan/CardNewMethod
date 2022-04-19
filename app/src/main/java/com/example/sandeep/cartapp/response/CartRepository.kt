package com.example.sandeep.cartapp.response

import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.di.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import com.example.sandeep.cartapp.view.product.utils.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CartRepository : SafeApiRequest() {

    private val retroService:ApiServices by lazy {

    RetroInstance.getRetrofitClient().create(ApiServices::class.java)
    }

    suspend fun getCartList() : ArrayList<CartData>? {

        return withContext( Dispatchers.IO){ retroService.getCart(CartBody()).body()?.data}
    }



    suspend fun updateData(data: UpdateData): UpdateResponse {
         return withContext(Dispatchers.IO){apiRequest { retroService.updateCart(data)}}

    }

    suspend fun deleteCart(data: DeleteData) : UpdateResponse{
        return withContext(Dispatchers.IO){apiRequest {
            retroService.deleteCart(data)}
        }
    }

}