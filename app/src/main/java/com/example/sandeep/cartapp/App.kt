package com.example.sandeep.cartapp

import android.app.Application
import com.example.sandeep.cartapp.di.RetroInstance
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.response.CartRepository
import com.example.sandeep.cartapp.view.product.allProduct.AllProductViewModel
import com.example.sandeep.cartapp.view.product.allProduct.AllProductViewModelProvider
import com.example.sandeep.cartapp.view.product.allProduct.repository.MainRepository
import com.example.sandeep.cartapp.view.product.cartItem.CartItemViewModel
import com.example.sandeep.cartapp.view.product.cartItem.CartItemViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.math.sin


class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
       bind() from singleton { RetroInstance() }
        bind() from singleton { ApiServices::class.java }
        bind() from singleton { CartRepository() }
        bind() from singleton { CartItemViewModel(instance()) }
        bind() from provider { CartItemViewModelProvider() }
        bind() from singleton { MainRepository() }
        bind() from singleton { AllProductViewModel(instance()) }
        bind() from provider { AllProductViewModelProvider() }

    }
}