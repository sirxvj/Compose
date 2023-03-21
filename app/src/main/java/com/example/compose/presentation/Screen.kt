package com.example.compose.presentation

sealed class Screen(val route:String){
    object ScheduleListScreen: Screen("Schedule_list")
}
