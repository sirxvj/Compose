package com.example.compose.domain.model

import com.example.compose.data.remote.dto.Employee
import com.example.compose.data.remote.dto.StudentGroup

data class MondayModel(
    val auditories: List<String>?,
    val endLessonTime: String?,
    val lessonTypeAbbrev: String?,
    val numSubgroup: Int?,
    val startLessonTime: String?,
    val subject: String?,
    val subjectFullName: String?,
    val weekNumber: List<Int>?
)