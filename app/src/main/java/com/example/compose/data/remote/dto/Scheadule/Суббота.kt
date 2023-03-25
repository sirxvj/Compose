package com.example.compose.data.remote.dto.Scheadule

import com.example.compose.domain.model.LessonModel

data class Суббота(
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
fun Суббота.toLessonModel(): LessonModel {
    val str = if(employees.isNotEmpty())
        employees[0].lastName+" "+employees[0].firstName[0]+"."+employees[0].middleName[0]+"."
    else
        ""
    return LessonModel(
        auditories = auditories,
        endLessonTime = endLessonTime,
        lessonTypeAbbrev = lessonTypeAbbrev,
        numSubgroup = numSubgroup,
        startLessonTime = startLessonTime,
        subject = subject,
        subjectFullName = subjectFullName,
        weekNumber = weekNumber,
        prepodFio = str
    )
}