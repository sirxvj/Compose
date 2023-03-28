package com.example.compose.data.remote

import com.example.compose.data.remote.dto.group.GroupDtoItem
import com.example.compose.data.remote.dto.Scheadule.MainDto
import com.example.compose.data.remote.dto.prepod.PrepodDto
import com.example.compose.data.remote.dto.prepodSchedule.PrepScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v1/schedule?")
    suspend fun getShedule(@Query("studentGroup") groupNumber : String): MainDto
    @GET("v1/schedule/current-week")
    suspend fun getCurrentWeek():Int
    @GET("v1/student-groups")
    suspend fun getGroups():List<GroupDtoItem>
    @GET("v1/employees/all")
    suspend fun getPrepod():List<PrepodDto>
    @GET("v1/employees/schedule/{urlId}")
    suspend fun getPrepShedule(@Path("urlId") urlId : String): PrepScheduleDto
}