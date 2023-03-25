package com.example.compose.presentation.list

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.presentation.MainViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            ScheduleListScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Search.route) {
            AddNewScreen(viewModel,navController)
        }
    }
}