package com.example.compose.data.repository

import android.util.Log
import com.example.compose.data.RetrofitInstance
import com.example.compose.data.remote.dto.toSchedules


import com.example.compose.domain.model.ScheduleModel
import com.example.compose.domain.repository.Repository

class RepositoryIMPL:Repository {
    override suspend fun getSchedule(groupNum:String): ScheduleModel {
        val ml : MutableList<ScheduleModel> = mutableListOf()
        val list =  RetrofitInstance.api.getShedule(groupNum)
        Log.e("GG",list.studentGroupDto.name)
        return list.toSchedules()
    }

    override suspend fun getCurrentWeek(): Int {
        val curWeek = RetrofitInstance.api.getCurrentWeek()
        Log.e("DFGHJKL",curWeek.toString())
        return curWeek
    }
}