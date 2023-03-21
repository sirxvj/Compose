package com.example.compose.data.remote.test

import com.example.compose.data.remote.dto.Model2
import com.example.compose.domain.model.*

data class blyat(
    val employeeDto: Any,
    val endDate: String,
    val endExamsDate: Any,
    val exams: List<Any>,
    val schedules: Schedules,
    val startDate: String,
    val startExamsDate: Any,
    val studentGroupDto: StudentGroupDto
)
fun blyat.toSchedules() : ScheduleModel {
    val mo = mutableListOf<MondayModel>()
    val tm = mutableListOf<TuesdayModel>()
    val wm = mutableListOf<WednesdayModel>()
    val th = mutableListOf<ThirsdayModel>()
    val fr = mutableListOf<FridayModel>()
    val st = mutableListOf<SaturdayModel>()
    with(schedules){
        Понедельник.onEach { mnd -> mo.add(mnd.toModel()) }
        Вторник.onEach { mnd -> tm.add(mnd.toModel()) }
        Среда.onEach { mnd -> wm.add(mnd.toModel()) }
        Четверг.onEach { mnd -> th.add(mnd.toModel()) }
        Пятница.onEach { mnd->fr.add(mnd.toModel()) }
        Суббота.onEach { mnd -> st.add(mnd.toModel()) }
    }
    return ScheduleModel(
        MondayList = mo.toList(),
        TuesdayList = tm.toList(),
        WednesdayList = wm.toList(),
        ThursdayList = th.toList(),
        FridayList = fr.toList(),
        SaturdayList = st.toList()
    )
}