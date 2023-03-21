package com.example.compose.data.remote.dto

data class Schedules(
    val Tuesday: List<Schedul>,
    val Monday: List<Schedul>,
    val Friday: List<Schedul>,
    val Wednesday: List<Schedul>,
    val Saturday: List<Schedul>,
    val Thirsday: List<Schedul>
)