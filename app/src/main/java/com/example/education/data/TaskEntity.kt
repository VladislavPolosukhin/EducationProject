package com.example.education.data

import java.util.*

data class TaskEntity(

    val id: Long,
    val positionWork: Int,
    val positionCompleted: Int,
    val title: String ,
    val info: String,
    val isCompleted: Boolean,
    val alarmAt: Date

)