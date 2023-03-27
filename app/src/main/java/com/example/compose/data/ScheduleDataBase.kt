package com.example.compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.compose.domain.model.*

@Database(entities = [ScheduleModel::class, LessonModel::class], version = 1)
@TypeConverters(StringListConverter::class, IntegerListConverter::class, LessonListConverter::class)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}