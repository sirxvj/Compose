package com.example.compose.presentation.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.data.remote.dto.*
import com.example.compose.domain.model.*


@Composable
fun MondayItem(
    schedule: MondayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}

@Composable
fun TuesdayItem(
    schedule: TuesdayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}

@Composable
fun WednesdayItem(
    schedule: WednesdayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}

@Composable
fun ThirsdayItem(
    schedule: ThirsdayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}

@Composable
fun FridayItem(
    schedule: FridayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}

@Composable
fun SaturdayItem(
    schedule: SaturdayModel
) {
    Card(
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(60.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Column() {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            val barColor: Color
            if (schedule.lessonTypeAbbrev == "ЛК") barColor = Color.Green
            else if (schedule.lessonTypeAbbrev == "ЛР") barColor = Color.Red
            else barColor = Color.Yellow
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(60.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(40.dp))
                    .background(barColor)
            )
            Text(
                text = schedule.subject!!,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }

    }
}