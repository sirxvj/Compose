package com.example.compose.data

import androidx.room.*
import com.example.compose.domain.model.LessonModel
import com.example.compose.domain.model.ScheduleModel
import com.example.compose.domain.model.ScheduleWithLessons

@Dao
interface ScheduleDao {
    @Transaction
    @Query("SELECT * FROM ScheduleModel")
    fun getAll(): List<ScheduleWithLessons>

    @Transaction
    @Query("SELECT * FROM ScheduleModel WHERE GroupNumb LIKE :numb LIMIT 1")
    fun findByNumb(numb: String): ScheduleWithLessons

    @Insert
    fun insertAll(vararg models: LessonModel)

    @Delete
    fun delete(model: ScheduleModel)

    @Delete
    fun delete(model: LessonModel)
}