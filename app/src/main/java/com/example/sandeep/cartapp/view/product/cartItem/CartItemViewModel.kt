package com.example.sandeep.cartapp.view.product.cartItem


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandeep.cartapp.response.CartRepository
import com.example.sandeep.cartapp.view.product.adaptor.*
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

            val status = repository.updateData(data = data)

            if (status==true){
                Log.e("Cart Update","Successfully updated")
            }else{
                Log.e("Cart Update","Fail updating")
            }

    }

    suspend fun deleteCart(data : DeleteData){


            val status = repository.deleteCart(data)

            if (status==true){
                val result =cardItem.value?.filter {
                    it.pkProductId!=data.strProductId
                }

                    result?.let {
                        val cartResponse = CartResponse(true,"Sucess",result.size,
                            result as ArrayList<CartData>?
                        )
                        cardItem.postValue(cartResponse.data)
                    }
                Log.e("Cart Update","Successfully delete")
            }else{
                Log.e("Cart Update","Fail delete")
            }


    }






}