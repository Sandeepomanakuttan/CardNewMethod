package com.example.sandeep.cartapp.response
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.network.RetroInstance
import com.example.sandeep.cartapp.view.product.adaptor.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CartRepository {

    private val retroService:ApiServices by lazy {

    RetroInstance.getRetofitClient().create(ApiServices::class.java)
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

}