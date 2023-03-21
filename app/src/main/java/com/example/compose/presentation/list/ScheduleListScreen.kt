package com.example.compose.presentation.list

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.compose.data.remote.dto.Thirsday
import com.example.compose.data.remote.dto.Wednesday
import com.example.compose.presentation.ComposeTheme
import com.example.compose.presentation.MainViewModel
import com.example.compose.presentation.list.component.*

@Composable
fun ScheduleListScreen(
    viewModel: MainViewModel
) {
    val searchtext = remember {
        mutableStateOf("")
    }
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF161616))
    ) {

        OutlinedTextField(
            value = searchtext.value,
            onValueChange = { searchtext.value = it },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                viewModel.getPrepods(searchtext.value)

            })
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 80.dp)
                .background(Color(0xff262626))

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                state.Days?.let {
                    items(it.MondayList) { lesson ->
                        MondayItem(schedule = lesson)
                    }
                    items(it.TuesdayList) { lesson ->
                        TuesdayItem(schedule = lesson)
                    }
                    items(it.WednesdayList) { lesson ->
                        WednesdayItem(schedule = lesson)
                    }
                    items(it.ThursdayList) { lesson ->
                        ThirsdayItem(schedule = lesson)
                    }
                    items(it.FridayList) { lesson ->
                        FridayItem(schedule = lesson)
                    }
                    items(it.SaturdayList) { lesson ->
                        SaturdayItem(schedule = lesson)
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