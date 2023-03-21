package com.example.compose.data.remote.dto

import com.example.compose.domain.model.*

data class Model2(
    val employeeDto: Any,
    val endDate: String,
    val endExamsDate: Any,
    val exams: List<Any>,
    val schedules: Schedules,
    val startDate: String,
    val startExamsDate: Any,
    val studentGroupDto: StudentGroupDto
)
//fun Model2.toSchedules() : ScheduleModel{
//    val mo = mutableListOf<MondayModel>()
//    val tm = mutableListOf<TuesdayModel>()
//    val wm = mutableListOf<WednesdayModel>()
//    val th = mutableListOf<ThirsdayModel>()
//    val fr = mutableListOf<FridayModel>()
//    val st = mutableListOf<SaturdayModel>()
//    with(schedules){
//        Monday.onEach { mnd -> mo.add(mnd.toMondayModel()) }
//        Tuesday.onEach { mnd -> tm.add(mnd.toTuesdayModel()) }
//        Wednesday.onEach { mnd -> wm.add(mnd.toWednesdayModel()) }
//        Thirsday.onEach { mnd -> th.add(mnd.toThirsdayModel()) }
//        Friday.onEach { mnd->fr.add(mnd.toFridayModel()) }
//        Saturday.onEach { mnd -> st.add(mnd.toSaturdayModel()) }
//    }
//    return ScheduleModel(
//        MondayList = mo.toList(),
//        TuesdayList = tm.toList(),
//        WednesdayList = wm.toList(),
//        ThursdayList = th.toList(),
//        FridayList = fr.toList(),
//        SaturdayList = st.toList()
//    )
//}