package com.example.compose.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ScheduleModel(
    @PrimaryKey
    val GroupNumb: String = ""
)

data class ScheduleWithLessons(
    @Embedded val scheduleModel: ScheduleModel,
    @Relation(
        parentColumn = "GroupNumb",
        entityColumn = "scheduleModelGroupNumb"
    )
    val TuesdayList: List<LessonModel>?,
    val MondayList: List<LessonModel>?,
    val FridayList: List<LessonModel>?,
    val WednesdayList: List<LessonModel>?,
    val SaturdayList: List<LessonModel>?,
    val ThursdayList: List<LessonModel>?
)