package com.example.compose.presentation.navigation

sealed class Screen(val route:String){
    object ScheduleListScreen: Screen("Schedule_list")
}
