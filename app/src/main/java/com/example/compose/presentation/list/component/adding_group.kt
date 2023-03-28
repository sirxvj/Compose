package com.example.compose.presentation.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.presentation.MainViewModel

@Composable
fun adding_newGroup(viewModel: MainViewModel,navController:NavController){
    val searchtext = remember {
        mutableStateOf("")
    }
    Box() {
        val state = viewModel.groupState.value
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = searchtext.value,
                shape = MaterialTheme.shapes.extraLarge,
                onValueChange = { newText ->
                    searchtext.value = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = androidx.compose.material.MaterialTheme.colors.background,
                    unfocusedBorderColor = androidx.compose.material.MaterialTheme.colors.background,
                    focusedLabelColor = androidx.compose.material.MaterialTheme.colors.primary,
                    cursorColor = androidx.compose.material.MaterialTheme.colors.background
                ),
                textStyle = TextStyle(color = Color.LightGray)
            )

            if (state.Groups.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.Groups) { itm ->
                        if (itm.name.contains(searchtext.value)) GroupItem(
                            group = itm,
                            viewModel,
                            navController
                        )
                    }
                }
            } else
                viewModel.getGroup()
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.error.isNotBlank())
            Text(
                text = "Something went wrong..." + state.error,
                fontSize = 20.sp,
                modifier = Modifier.align(
                    Alignment.Center
                ),
                color = Color.LightGray
            )
    }
}