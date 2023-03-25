package com.example.compose.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import com.example.compose.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.presentation.MainViewModel
import com.example.compose.presentation.list.component.GroupItem

@Composable
fun AddNewScreen(viewModel: MainViewModel, navController: NavController) {
    val searchtext = remember {
        mutableStateOf("")
    }
    val state = viewModel.groupState.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.DarkGray)
                    .padding(start = 10.dp, top = 10.dp)
            ) {

                Row() {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.back_arrow),
                        contentDescription = "sdcsdc",
                        modifier = Modifier
                            .padding(top = 8.dp, end = 15.dp, start = 5.dp)
                            .size(25.dp, 25.dp)
                            .clickable { navController.navigate(Screen.Home.route) })

                    Text(
                        text = "Add new Scheadule",
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            OutlinedTextField(
                value = searchtext.value,
                shape = androidx.compose.material3.MaterialTheme.shapes.extraLarge,
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
                    focusedBorderColor = MaterialTheme.colors.background,
                    unfocusedBorderColor = MaterialTheme.colors.background,
                    focusedLabelColor = MaterialTheme.colors.primary,
                    cursorColor = MaterialTheme.colors.background
                ),
                textStyle = TextStyle(color = Color.LightGray)
            )

            if (state.Groups.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.Groups){itm->
                       if(itm.name.contains(searchtext.value)) GroupItem(group = itm,viewModel,navController)
                    }
                }
            }
            else
                viewModel.getGroup()

        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
        if(state.error.isNotBlank())
            Text(text = "Something went wrong..."+state.error,fontSize = 20.sp, modifier = Modifier.align(Center),color = Color.LightGray)
    }
}