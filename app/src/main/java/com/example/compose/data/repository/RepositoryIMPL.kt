package com.example.compose.data.repository

import android.util.Log
import com.example.compose.data.RetrofitInstance
import com.example.compose.data.remote.dto.group.toGroupModel
import com.example.compose.data.remote.dto.Scheadule.toSchedules
import com.example.compose.domain.model.GroupModel


import com.example.compose.domain.model.ScheduleModel
import com.example.compose.domain.repository.Repository

class RepositoryIMPL:Repository {
    override suspend fun getSchedule(groupNum:String): ScheduleModel {
        Log.e("TAg",RetrofitInstance.api.getShedule(groupNum).startDate)
        val list =  RetrofitInstance.api.getShedule(groupNum)
        Log.e("GG",list.studentGroupDto.name)
        return list.toSchedules()
    }

    override suspend fun getGroups(): List<GroupModel> {
        val ml : MutableList<GroupModel> = mutableListOf();
        for(a in RetrofitInstance.api.getGroups()){
            ml.add(a.toGroupModel())
        }
        Log.e("sdfsdfsd",ml[0].specialityName)
        return ml
    }
    override suspend fun getCurrentWeek(): Int {
        val curWeek = RetrofitInstance.api.getCurrentWeek()
        Log.e("DFGHJKL",curWeek.toString())
        return curWeek
    }
}