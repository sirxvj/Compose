package com.example.compose.data.remote


import com.example.compose.data.remote.dto.MainDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/schedule?")
    suspend fun getShedule(@Query("studentGroup") groupNumber : String): MainDto
    @GET("v1/schedule/current-week")
    suspend fun getCurrentWeek():Int
}