package com.example.sandeep.cartapp.view.product.allProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sandeep.cartapp.view.product.allProduct.repository.MainRepository
import java.lang.IllegalArgumentException

class AllProductViewModelProvider : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllProductViewModel::class.java)){
            return AllProductViewModel(repository = MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown model class")
    }
}