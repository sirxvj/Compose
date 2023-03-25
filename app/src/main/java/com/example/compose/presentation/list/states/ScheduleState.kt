package com.example.compose.presentation.list.states

import com.example.compose.domain.model.ScheduleModel

data class ScheduleState(
    val isLoading: Boolean = false,
    var Days : ScheduleModel? = null,
    val error:String = ""
)
