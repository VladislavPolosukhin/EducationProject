package com.example.education.database

import android.content.ContentValues
import android.database.Cursor
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import java.util.*

object CursorParser {

    fun getContentValues(task: TaskEntity): ContentValues {
        return ContentValues().apply {
            put(DbTaskConst.POSITION_WORK, task.positionWork)
            put(DbTaskConst.POSITION_COMPLETED, task.positionCompleted)
            put(DbTaskConst.TITLE, task.title)
            put(DbTaskConst.INFO, task.info)
            put(DbTaskConst.IS_COMPLETED, task.isCompleted)
            put(DbTaskConst.ALARM_AT, task.alarmAt.time)
        }
    }


    fun getTaskEntity(cursor: Cursor): TaskEntity {
        return TaskEntity(
            id = cursor.getLong(cursor.getColumnIndex(DbTaskConst.ID)),
            positionWork = cursor.getInt(cursor.getColumnIndex(DbTaskConst.POSITION_WORK)),
            positionCompleted = cursor.getInt(cursor.getColumnIndex(DbTaskConst.POSITION_COMPLETED)),
            title = cursor.getString(cursor.getColumnIndex(DbTaskConst.TITLE)),
            info = cursor.getString(cursor.getColumnIndex(DbTaskConst.INFO)),
            isCompleted = cursor.getInt(cursor.getColumnIndex(DbTaskConst.IS_COMPLETED)) > 0,
            alarmAt = Date(cursor.getLong(cursor.getColumnIndex(DbTaskConst.ALARM_AT)))
        )
    }

}