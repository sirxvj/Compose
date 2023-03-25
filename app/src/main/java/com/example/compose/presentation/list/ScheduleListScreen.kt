package com.example.compose.presentation.list

import com.example.compose.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.presentation.MainViewModel

import com.example.compose.presentation.list.component.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleListScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val searchtext = remember {
        mutableStateOf("")
    }
    val state = viewModel.state.value
    val weekState = viewModel.weekState.value
    val coroutineScope = rememberCoroutineScope()
    val CurrentGroup = remember {
        mutableStateOf("None")
    }
    val text = remember {
        mutableStateOf("sdcnjksdmlkkcsd")
    }
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    val barClickedState = remember {
        mutableStateOf(false)
    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // .height(300.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(20.dp))
                    .background(Color.DarkGray)

            ) {

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    val Addedlist = viewModel.getGroups()
                    items(Addedlist) { iter ->
                        Text(
                            text = iter,
                            fontSize = 30.sp,
                            color = Color.LightGray,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.getScheadule(iter);viewModel.getCurrentWeek(); CurrentGroup.value =
                                    iter
                                    coroutineScope.launch { sheetState.collapse() }
                                })

                    }
                    item {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(route = Screen.Search.route)
                            }) {
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.plus),
                                contentDescription = "fghjk",
                                modifier = Modifier
                                    .height(70.dp)
                                    .width(70.dp)
                                    .padding(5.dp)
                            )
                            Text(
                                text = "Add new",
                                fontSize = 30.sp,
                                color = Color.LightGray,
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                    }
                }


            }
        },

        sheetPeekHeight = 0.dp

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.DarkGray)
                    .padding(start = 10.dp, top = 10.dp)
            ) {
                Row(modifier = Modifier
                    // .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            barClickedState.value = !barClickedState.value
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }
                        }
                    }
                ) {
                    Image(
                        imageVector = if (barClickedState.value) ImageVector.vectorResource(R.drawable.hide)
                        else
                            ImageVector.vectorResource(R.drawable.show),
                        contentDescription = "qwertyui",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                        // .padding(top = 15.dp, start = 15.dp)
                    )
                    Text(
                        text = CurrentGroup.value,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 6.dp),
                        color = Color.LightGray
                    )
                }
            }
            if (state.Days != null && weekState.week != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp)
                        .background(Color.Black)

                ) {

                    @Suppress("NAME_SHADOWING") val it = state.Days!!
                    val date = mutableStateOf(LocalDate.now())
                    date.value = LocalDate.now()
                    var upcnt = 0
                    var txt = ""
                    upcnt = weekState.week ?: 1
                    while (date.value.month.value < 6 || (date.value.month.value in 9..12)) {
                        val cnt = upcnt
                        var list = it.MondayList
                        when (date.value.dayOfWeek.value) {
                            1 -> list = it.MondayList
                            2 -> list = it.TuesdayList
                            3 -> list = it.WednesdayList
                            4 -> list = it.ThursdayList
                            5 -> list = it.FridayList
                            6 -> list = it.SaturdayList
                        }
                        var month = ""
                        for (n in date.value.month.toString().indices)
                            if (n > 0) month += date.value.month.toString()[n].lowercase()
                            else month += date.value.month.toString()[n]

                        var dayOfweek = ""
                        for (n in date.value.dayOfWeek.toString().indices)
                            if (n > 0) dayOfweek += date.value.dayOfWeek.toString()[n].lowercase()
                            else dayOfweek += date.value.dayOfWeek.toString()[n]

                        txt =
                            month + " " + date.value.dayOfMonth.toString() + ", " + dayOfweek + ", week " + cnt.toString()
                        for (n in list) {
                            var found = false
                            for (j in n.weekNumber!!) {
                                if (j == cnt) {
                                    items(mutableListOf(txt)) { itm ->
                                        Text(
                                            text = itm, color = Color.LightGray,
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                top = 10.dp,
                                                bottom = 0.dp
                                            )
                                        )
                                    }
                                    found = true
                                    break
                                }
                            }
                            if (found) break
                        }
                        items(list) { iter ->
                            LessonItem(schedule = iter, week = cnt)
                        }
                        if (date.value.dayOfWeek.toString() != "SATURDAY") date.value =
                            date.value.plusDays(1)
                        else {
                            date.value = date.value.plusDays(2)
                            upcnt++;upcnt %= 5
                            if (upcnt == 0) upcnt++
                        }
                    }
                }
            } else if (!state.isLoading) {
                Text(
                    text = "No Schedule found((((....",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
                if (viewModel.getGroups().size > 0 && CurrentGroup.value != "None") {
                    viewModel.getScheadule(CurrentGroup.value)
                    viewModel.getCurrentWeek()
                    //CurrentGroup.value = viewModel.getGroups()[0]
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
