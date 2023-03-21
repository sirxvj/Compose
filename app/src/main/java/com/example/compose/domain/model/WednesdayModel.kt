package com.example.compose.domain.model

data class WednesdayModel(
    val auditories: List<String>?,
    val endLessonTime: String?,
    val lessonTypeAbbrev: String?,
    val numSubgroup: Int?,
    val startLessonTime: String?,
    val subject: String?,
    val subjectFullName: String?,
    val weekNumber: List<Int>?
)