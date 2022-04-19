package com.example.sandeep.cartapp.view.product.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutine {

    fun main(work : suspend (()-> Unit))= CoroutineScope(Dispatchers.Main).launch {
        work()
    }
}