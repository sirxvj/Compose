package com.example.compose.presentation.list.component

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.commmon.constants
import com.example.compose.commmon.constants.ADDEDPREPS
import com.example.compose.presentation.MainViewModel
import com.example.compose.presentation.list.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSHIIT(viewModel: MainViewModel,navController:NavController,coroutineScope:CoroutineScope,sheetState: BottomSheetState){
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(viewModel.getGroups()) { iter ->
            Text(
                text = iter,
                fontSize = 30.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.getScheadule(iter); viewModel.headertext.value =
                        iter
                        coroutineScope.launch { sheetState.collapse() }
                    })

        }
        items(viewModel.getPrepods()) { iter ->
            val prefs = viewModel.context.getSharedPreferences(ADDEDPREPS, Context.MODE_PRIVATE)
            val urlidd = prefs.getString(iter,"")?:""
            Text(
                text = iter,
                fontSize = 30.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.getPrepodScheadule(urlidd); viewModel.headertext.value =
                        iter
                        Log.e("TAGG",urlidd)
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
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}