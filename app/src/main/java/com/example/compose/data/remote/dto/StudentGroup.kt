package com.example.compose.data.remote.dto

data class StudentGroup(
    val educationDegree: Int,
    val name: String,
    val numberOfStudents: Int,
    val specialityCode: String,
    val specialityName: String
)