package com.example.compose.presentation.list.states

import com.example.compose.domain.model.ScheduleWithLessons

data class ScheduleState(
    val isLoading: Boolean = false,
    var Days : ScheduleWithLessons? = null,
    val error:String = ""
)
