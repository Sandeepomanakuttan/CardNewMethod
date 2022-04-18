package com.example.sandeep.cartapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroInstance {

    companion object{

        var retrofit:Retrofit?=null
        val apiServices: ApiServices?=null


        private val base="https://www.matajar.ae/v2/api/"

         fun getRetofitClient():Retrofit {

             val logging =HttpLoggingInterceptor()
             logging.level=(HttpLoggingInterceptor.Level.BODY)
             val client = OkHttpClient.Builder()
             client.addInterceptor(logging)
            if (retrofit ==null){
                retrofit = Retrofit.Builder().
                baseUrl(base)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }





    fun getMovieApi(): ApiServices? {
        return apiServices
    }
}