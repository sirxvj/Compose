package com.example.compose.data.remote.dto

import com.example.compose.domain.model.FridayModel
import com.example.compose.domain.model.SaturdayModel

data class Saturday(
    val announcement: Boolean,
    val announcementEnd: Any,
    val announcementStart: Any,
    val auditories: List<String>,
    val dateLesson: Any,
    val employees: List<Employee>,
    val endLessonDate: String,
    val endLessonTime: String,
    val lessonTypeAbbrev: String,
    val note: String,
    val numSubgroup: Int,
    val split: Boolean,
    val startLessonDate: String,
    val startLessonTime: String,
    val studentGroups: List<StudentGroup>,
    val subject: String,
    val subjectFullName: String,
    val weekNumber: List<Int>
)
fun Saturday.toSaturdayModel(): SaturdayModel {
    return SaturdayModel(
        auditories = auditories,
        endLessonTime = endLessonTime,
        lessonTypeAbbrev = lessonTypeAbbrev,
        numSubgroup = numSubgroup,
        startLessonTime = startLessonTime,
        subject = subject,
        subjectFullName = subjectFullName,
        weekNumber = weekNumber
    )
}