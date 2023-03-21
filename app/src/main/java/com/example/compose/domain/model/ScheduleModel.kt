package com.example.compose.domain.model

data class ScheduleModel(
    val TuesdayList: List<LessonModel>,
    val MondayList: List<LessonModel>,
    val FridayList: List<LessonModel>,
    val WednesdayList: List<LessonModel>,
    val SaturdayList: List<LessonModel>,
    val ThursdayList: List<LessonModel>
)