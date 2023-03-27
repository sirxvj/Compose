package com.example.compose.domain.repository

import com.example.compose.domain.model.GroupModel
import com.example.compose.domain.model.ScheduleWithLessons

interface Repository {
    suspend fun getSchedule(groupNum:String):ScheduleWithLessons
    suspend fun getCurrentWeek():Int

    suspend fun getGroups():List<GroupModel>
}