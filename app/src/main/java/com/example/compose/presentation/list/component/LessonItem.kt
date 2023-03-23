package com.example.compose.presentation.list.component

import android.graphics.Paint.Align
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.data.remote.dto.*
import com.example.compose.domain.model.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun LessonItem(
    schedule: LessonModel,
    week: Int
) {
    for (n in schedule.weekNumber!!.indices) {
        if (schedule.weekNumber[n] == week)
            break
        if(n == schedule.weekNumber.size-1)
            return
    }
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
            Column(
               horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = schedule.startLessonTime.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 13.dp, start = 10.dp)
                )
                Text(
                    text = schedule.endLessonTime.toString(),
                    fontSize = 12.sp,
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
            val audit = remember { mutableStateOf("") }
            if (schedule.auditories?.size!! > 0)
                audit.value = schedule.auditories[0]
            Column() {
                Text(
                    text = schedule.subject!!,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 13.dp)
                )
                Text(
                    text = audit.value,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                )
            }
            val shit = remember{mutableStateOf("")}
            shit.value = "Нед. "
            for (n in schedule.weekNumber.toString()) {
                if (n != '[' && n != ']')
                    shit.value += n
            }
            if (schedule.weekNumber.size == 4) shit.value = ""
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = schedule.prepodFio + "\n" + shit.value,
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp, top = 10.dp),
                    textAlign = TextAlign.End,
                )

            }
        }

    }
}
