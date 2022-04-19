package com.example.sandeep.cartapp.network


import com.example.sandeep.cartapp.view.product.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {


    @POST("cart/get_cart")
    suspend fun getCart(@Body items: CartBody) : Response<CartResponse>

    @POST("cart/update_cart")
    suspend fun updateCart(@Body items: UpdateData) : Response<UpdateResponse>

    @POST("cart/delete_cart_item")
    suspend fun deleteCart(@Body data: DeleteData): Response<UpdateResponse>

    @POST("cart/add_cart")
    suspend fun addCart(@Body addBody: AddCart): Response<AddCrtResponse>

    @POST("product/get_product_under_category")
    suspend fun getProduc(@Body dataObj: ProductBody): ProductResponse

}

