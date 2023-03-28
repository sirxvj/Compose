package com.example.compose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*


import androidx.activity.compose.setContent

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.compose.presentation.list.ScheduleListScreen
import com.example.compose.presentation.list.SetUpNavGraph
import org.w3c.dom.Text


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()
        viewModel.context = this.applicationContext
        viewModel.getCurrentWeek()
        setContent {
            ComposeTheme(
                darkTheme = true,
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                }
                navController = rememberNavController()
                SetUpNavGraph(navController = navController,viewModel = viewModel)
                //ScheduleListScreen( viewModel = viewModel)
            }
        }
    }
}

