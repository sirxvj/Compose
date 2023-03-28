package com.example.compose.presentation.list.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.domain.model.LessonModel
import com.example.compose.domain.model.PrepodModel
import com.example.compose.presentation.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun addingPrepod(viewModel: MainViewModel, navController: NavController){
    val searchtext = remember {
        mutableStateOf("")
    }
    val prList = remember {
        mutableListOf<PrepodModel>()
    }
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        val state = viewModel.prepState.value
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = searchtext.value,
                shape = MaterialTheme.shapes.extraLarge,
                onValueChange = { newText ->
                  coroutineScope.launch {
                      searchtext.value = newText
                      if (searchtext.value.length == 1) {
                          prList.clear()
                          for (n in state.preps ?: emptyList()) {
                              if (n.fio.lowercase()[0] == searchtext.value[0]) {
                                  prList.add(n)
                              }
                          }
                      }
                      else if(searchtext.value.isEmpty()){
                          prList.clear()
                          prList.addAll(state.preps?: emptyList())
                      }
                  }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Black),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = androidx.compose.material.MaterialTheme.colors.background,
                    unfocusedBorderColor = androidx.compose.material.MaterialTheme.colors.background,
                    focusedLabelColor = androidx.compose.material.MaterialTheme.colors.primary,
                    cursorColor = androidx.compose.material.MaterialTheme.colors.background
                ),
                textStyle = TextStyle(color = Color.LightGray)
            )
            if (state.preps?.isNotEmpty() == true) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    if(searchtext.value.isEmpty() || prList.isEmpty())
                        prList.addAll(state.preps!!)
                    items(prList) { itm ->
                       if(itm.lastName.lowercase().contains(searchtext.value.lowercase()))PrepItem(prep = itm, viewModel = viewModel, navController = navController)
                    }
                }
            } else
                viewModel.getPrepod()
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.error.isNotBlank())
            androidx.compose.material.Text(
                text = "Something went wrong..." + state.error,
                fontSize = 20.sp,
                modifier = Modifier.align(
                    Alignment.Center
                ),
                color = Color.LightGray
            )
    }
}
