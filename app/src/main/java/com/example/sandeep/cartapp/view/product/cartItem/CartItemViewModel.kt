package com.example.sandeep.cartapp.view.product.cartItem


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandeep.cartapp.response.CartRepository
import com.example.sandeep.cartapp.view.product.model.CartData
import com.example.sandeep.cartapp.view.product.model.CartResponse
import com.example.sandeep.cartapp.view.product.model.DeleteData
import com.example.sandeep.cartapp.view.product.model.UpdateData
import com.example.sandeep.cartapp.view.product.utils.APiException
import kotlinx.coroutines.launch


class CartItemViewModel(private val repository: CartRepository) : ViewModel() {


    val cardItem : MutableLiveData<ArrayList<CartData>?> = MutableLiveData()

    init {
        viewModelScope.launch {
            cardItem.value=repository.getCartList()


        }


    }

    suspend fun get(){
        cardItem.value=repository.getCartList()
    }

    suspend fun updateCart(data: UpdateData) {

        try {
            val response = repository.updateData(data = data)

            response.success?.let {
                if (it){
                    Log.e("Update response","update $it")
                    return
                }else {
                    Log.e("Update response", "update $it")
                    return@let
                }


            }
        }catch (e: APiException){
            Log.e("Update response",e.message!!)
        }



    }

    suspend fun deleteCart(data : DeleteData){

            try {
                val response = repository.deleteCart(data)

                response.success?.let {
                    if (it){
                        val result =cardItem.value?.filter { its ->
                            its.pkProductId!=data.strProductId
                        }
                        result?.let {
                            val cartResponse = CartResponse(true,"Sucess",result.size,
                                result as ArrayList<CartData>?
                            )
                            cardItem.postValue(cartResponse.data)
                        }
                        Log.e("Delete response","Delete $it")
                        return
                    }else {
                        Log.e("Delete response", "Delete $it")
                        return@let
                    }
                }
            }catch (e:APiException){
                Log.e("Delete response", e.message!!)
            }


    }






}