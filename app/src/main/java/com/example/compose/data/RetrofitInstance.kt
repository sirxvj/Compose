package com.example.compose.data

import com.example.compose.commmon.constants.BASEURL
import com.example.compose.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(
            GsonConverterFactory.create()).build()
    }
    val api: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}