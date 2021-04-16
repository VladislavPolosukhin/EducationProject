package com.example.education.repository.mapper

import android.database.Cursor
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import com.example.education.utils.CursorToDataMapper
import java.util.*

object CursorToTaskEntityMapper : CursorToDataMapper<TaskEntity> {

    override fun map(cursor: Cursor): TaskEntity {
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