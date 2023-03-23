package com.example.compose.data.remote.dto

import com.example.compose.domain.model.*

data class MainDto(
    val employeeDto: Any,
    val endDate: String,
    val endExamsDate: Any,
    val exams: List<Any>,
    val schedules: Schedules,
    val startDate: String,
    val startExamsDate: Any,
    val studentGroupDto: StudentGroupDto
)
fun MainDto.toSchedules() : ScheduleModel {
    val mo = mutableListOf<LessonModel>()
    val tm = mutableListOf<LessonModel>()
    val wm = mutableListOf<LessonModel>()
    val th = mutableListOf<LessonModel>()
    val fr = mutableListOf<LessonModel>()
    val st = mutableListOf<LessonModel>()
    with(schedules){
        Понедельник.onEach { mnd -> mo.add(mnd.toLessonModel()) }
        Вторник.onEach { mnd -> tm.add(mnd.toLessonModel()) }
        Среда.onEach { mnd -> wm.add(mnd.toLessonModel()) }
        Четверг.onEach { mnd -> th.add(mnd.toLessonModel()) }
        Пятница.onEach { mnd->fr.add(mnd.toLessonModel()) }
        Суббота.onEach { mnd -> st.add(mnd.toLessonModel()) }
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