package com.example.sandeep.cartapp.view.product

import com.example.sandeep.cartapp.view.product.adaptor.AddCart
import com.example.sandeep.cartapp.view.product.adaptor.DeleteData
import com.example.sandeep.cartapp.view.product.adaptor.UpdateData

interface UtilInterface {

    suspend fun updateCart(data : UpdateData)
    suspend fun deleteCart(data: DeleteData)
}

interface MainInterface{
    suspend fun addCart(data:AddCart)
}