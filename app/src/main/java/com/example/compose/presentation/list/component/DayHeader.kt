package com.example.compose.presentation.list.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DayHeader(txt : String){
    Text(text = txt, color = Color.LightGray)
}