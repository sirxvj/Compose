package com.example.compose.domain.model

data class ScheduleModel(
    val TuesdayList: List<TuesdayModel>,
    val MondayList: List<MondayModel>,
    val FridayList: List<FridayModel>,
    val WednesdayList: List<WednesdayModel>,
    val SaturdayList: List<SaturdayModel>,
    val ThursdayList: List<ThirsdayModel>
)