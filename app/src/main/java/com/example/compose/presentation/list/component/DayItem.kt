package com.example.compose.presentation.list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.compose.domain.model.LessonModel
import com.example.compose.presentation.ScheduleState

@Composable
fun DayItem(
    week: Int,
    dayofWeek: String,
    state:ScheduleState
) {
    var list = listOf<LessonModel>()
    if(dayofWeek == "Понедельник")
        list = state.Days!!.MondayList
    else if(dayofWeek == "Вторник")
        list = state.Days!!.TuesdayList
    else if(dayofWeek == "Среда")
        list = state.Days!!.WednesdayList
    else if(dayofWeek == "Четверг")
        list = state.Days!!.ThursdayList
    else if(dayofWeek == "Пятница")
        list = state.Days!!.FridayList
    else if(dayofWeek == "Суббота")
        list = state.Days!!.SaturdayList
    Text(text = dayofWeek + ", неделя " + week.toString(),
        color = Color.LightGray
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        list.onEach {
            items(list){
                //LessonItem(schedule = it)
            }
        }
    }
}