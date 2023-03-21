package com.example.compose.data.remote


import com.example.compose.data.remote.dto.Model2
import com.example.compose.data.remote.test.blyat
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/schedule?")
    suspend fun getShedule(@Query("studentGroup") groupNumber : String): blyat
}