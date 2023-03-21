package com.example.compose.presentation.list

import android.app.LauncherActivity.ListItem
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.compose.domain.model.LessonModel
import com.example.compose.presentation.ComposeTheme
import com.example.compose.presentation.MainViewModel
import com.example.compose.presentation.list.component.*
import java.time.LocalDate

@Composable
fun ScheduleListScreen(
    viewModel: MainViewModel
) {
    val searchtext = remember {
        mutableStateOf("")
    }
    val state = viewModel.state.value
    val weekState = viewModel.weekState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        OutlinedTextField(
            value = searchtext.value,
            onValueChange = { searchtext.value = it },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                viewModel.getPrepods(searchtext.value)
                viewModel.getCurrentWeek()
            })
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
                .background(Color.Black)

        ) {
            if (state.Days != null && weekState.week!=null) {
                val it = state.Days!!
                val date = mutableStateOf(LocalDate.now())
                date.value = LocalDate.now()
                var cnt = 0
                var txt = ""
                cnt = weekState.week ?: 1
                while (date.value.month.value < 6) {
                    var list = it.MondayList
                    val weekStr = mutableStateOf("")
                    when (date.value.dayOfWeek.value) {
                        1 -> list = it.MondayList
                        2 -> list = it.TuesdayList
                        3 -> list = it.WednesdayList
                        4 -> list = it.ThursdayList
                        5 -> list = it.FridayList
                        6 -> list = it.SaturdayList
                    }
                    txt = date.value.month.toString()+" "+date.value.dayOfMonth.toString()+" " +date.value.dayOfWeek.toString() + ", week " + cnt.toString()
                    val hdList = listOf(txt)
                    items(hdList) { itm ->
                        Text(text = itm, color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 0.dp))
                    }

                    items(list) {iter ->
                        LessonItem(schedule = iter, week = cnt)
                    }
                    if (date.value.dayOfWeek.toString() != "SATURDAY") date.value =
                        date.value.plusDays(1)
                    else {
                        date.value = date.value.plusDays(2)
                        cnt++;cnt%=5
                        if(cnt == 0) cnt++
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}