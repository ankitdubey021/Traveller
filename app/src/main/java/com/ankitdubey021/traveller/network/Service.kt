package com.creativeitem.academy.latest.network

import com.ankitdubey021.traveller.model.BASE_URL
import com.ankitdubey021.traveller.network.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieNetwork{

    var logging = HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }

    var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()


    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val api = retrofit.create(Api::class.java)

}


