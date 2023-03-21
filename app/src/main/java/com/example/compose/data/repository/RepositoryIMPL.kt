package com.example.compose.data.repository

import android.util.Log
import com.example.compose.data.RetrofitInstance
import com.example.compose.data.remote.test.toSchedules


import com.example.compose.domain.model.ScheduleModel
import com.example.compose.domain.repository.Repository
import kotlinx.coroutines.delay
import java.util.concurrent.Delayed

class RepositoryIMPL:Repository {
    override suspend fun getSchedule(groupNum:String): ScheduleModel {
        val ml : MutableList<ScheduleModel> = mutableListOf()
        val list =  RetrofitInstance.api.getShedule(groupNum)
        Log.e("GG",list.studentGroupDto.name)
        return list.toSchedules()
    }
}