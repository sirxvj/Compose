package com.example.compose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class LessonModel(
    @PrimaryKey(autoGenerate = true)
    val key: Int = 0,
    var id: String,
    val auditories: List<String>?,
    val endLessonTime: String?,
    val lessonTypeAbbrev: String?,
    val numSubgroup: Int,
    val startLessonTime: String?,
    val subject: String?,
    val subjectFullName: String?,
    val weekNumber: List<Int>?,
    val fio:String,
    val note : String?,
    val weekDay : String
)

class StringListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(", ")
    }
}
class IntegerListConverter {
    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        return value?.split(",")?.map { it.trim().toInt() }
    }

    @TypeConverter
    fun toString(list: List<Int>?): String? {
        return list?.joinToString(",")
    }
}