package com.example.compose.presentation.list

sealed class Screen(val route:String){
    object Home:Screen("Sheadule")
    object Search:Screen("Group")
}
