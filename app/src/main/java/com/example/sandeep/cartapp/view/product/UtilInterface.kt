package com.example.sandeep.cartapp.view.product

import com.example.sandeep.cartapp.view.product.model.AddCart
import com.example.sandeep.cartapp.view.product.model.DeleteData
import com.example.sandeep.cartapp.view.product.model.UpdateData

interface UtilInterface {

    suspend fun updateCart(data : UpdateData)
    suspend fun deleteCart(data: DeleteData)
}

interface MainInterface{
    suspend fun addCart(data: AddCart)
}