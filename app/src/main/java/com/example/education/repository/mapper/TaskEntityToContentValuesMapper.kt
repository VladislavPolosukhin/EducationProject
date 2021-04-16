package com.example.education.repository.mapper

import android.content.ContentValues
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity

fun TaskEntity.toContentValues(): ContentValues {
    val task = this
    return ContentValues().apply {
        put(DbTaskConst.POSITION_WORK, task.positionWork)
        put(DbTaskConst.POSITION_COMPLETED, task.positionCompleted)
        put(DbTaskConst.TITLE, task.title)
        put(DbTaskConst.INFO, task.info)
        put(DbTaskConst.IS_COMPLETED, task.isCompleted)
        put(DbTaskConst.ALARM_AT, task.alarmAt.time)
    }
}