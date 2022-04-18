package com.example.sandeep.cartapp.view.product.cartItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sandeep.cartapp.response.CartRepository
import java.lang.IllegalArgumentException

class CartItemViewModelProvider : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartItemViewModel::class.java)){
            return CartItemViewModel(repository = CartRepository()) as T
        }
        throw IllegalArgumentException("UNknown model class")

    }
}