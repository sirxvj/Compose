package com.example.compose.presentation

import com.example.compose.domain.model.ScheduleModel

data class CurrentWeekState(
    val isLoading: Boolean = false,
    var week : Int? = 0,
    val error:String = ""
)
