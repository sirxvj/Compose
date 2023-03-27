package com.example.compose.data.remote.dto.Scheadule

import com.example.compose.domain.model.*

data class MainDto(
    val employeeDto: Employee?,
    val endDate: String,
    val endExamsDate: String?,
    val exams: List<Any>,
    val schedules: Schedules,
    val startDate: String,
    val startExamsDate: String?,
    val studentGroupDto: StudentGroupDto
)
fun MainDto.toSchedules() : ScheduleWithLessons {
    val mo = mutableListOf<LessonModel>()
    val tm = mutableListOf<LessonModel>()
    val wm = mutableListOf<LessonModel>()
    val th = mutableListOf<LessonModel>()
    val fr = mutableListOf<LessonModel>()
    val st = mutableListOf<LessonModel>()
    with(schedules){
        Monday?.onEach { mnd -> mo.add(mnd.toLessonModel()) }
        Tuesday?.onEach { mnd -> tm.add(mnd.toLessonModel()) }
        Wednesday?.onEach { mnd -> wm.add(mnd.toLessonModel()) }
        Thursday?.onEach { mnd -> th.add(mnd.toLessonModel()) }
        DayDto?.onEach { mnd->fr.add(mnd.toLessonModel()) }
        Saturday?.onEach { mnd -> st.add(mnd.toLessonModel()) }
    }
    return ScheduleWithLessons(
        ScheduleModel(GroupNumb =studentGroupDto.name),
        MondayList = mo.toList(),
        TuesdayList = tm.toList(),
        WednesdayList = wm.toList(),
        ThursdayList = th.toList(),
        FridayList = fr.toList(),
        SaturdayList = st.toList())

}