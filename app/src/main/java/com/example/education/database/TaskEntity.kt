package com.example.education.database

import java.util.*

data class TaskEntity(
    val id: Int,
    val positionWork: Int,
    val positionCompleted: Int,
    val title: String ,
    val info: String,
    val isCompleted: Boolean,
    val alarmAt: Date
)