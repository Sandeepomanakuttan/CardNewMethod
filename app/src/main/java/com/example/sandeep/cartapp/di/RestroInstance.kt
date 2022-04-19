package com.example.sandeep.cartapp.di


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{

        var retrofit:Retrofit?=null


        private const val base="https://www.matajar.ae/v2/api/"

         fun getRetrofitClient():Retrofit {

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

}