package com.example.compose.domain.repository

import com.example.compose.domain.model.ScheduleModel

interface Repository {
    suspend fun getSchedule(groupNum:String):ScheduleModel
}