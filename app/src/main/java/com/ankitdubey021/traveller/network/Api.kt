package com.ankitdubey021.traveller.network

import com.ankitdubey021.traveller.model.Place
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("tours")
    suspend fun getPosts(): Response<List<Place>>

}