package com.example.education.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import java.util.*

interface TaskRepository {

    fun insertTask(task: TaskEntity): Long
    fun getTasksByPage(fromPosition: Int, count: Int): List<TaskEntity>
    fun deleteTaskById(id: Long): Int
    fun updateTask(task: TaskEntity): Int
    // TODO добавить методов

}

class TaskRepositoryImpl(
    private val db: SQLiteDatabase
) : TaskRepository {

    override fun insertTask(task: TaskEntity): Long {
        return db.insertOrThrow(
            DbTaskConst.TABLE,
            null,
            ContentValues().apply {
                //put(DbTaskConst.ID, task.id)
                put(DbTaskConst.POSITION_WORK, task.positionWork)
                put(DbTaskConst.POSITION_COMPLETED, task.positionCompleted)
                put(DbTaskConst.TITLE, task.title)
                put(DbTaskConst.INFO, task.info)
                put(DbTaskConst.IS_COMPLETED, task.isCompleted)
                put(DbTaskConst.ALARM_AT, task.alarmAt.time)
            }
        )
    }

    override fun getTasksByPage(fromPosition: Int, count: Int): List<TaskEntity> {
        val list = mutableListOf<TaskEntity>()
        val query = """|SELECT
            |    *
            |FROM ${DbTaskConst.TABLE}
            |LIMIT $count OFFSET $fromPosition
         """.trimMargin()
        db.rawQuery(query, null).use { cursor ->
            while (cursor.moveToNext()) {
                list.add(
                    TaskEntity(
                        id = cursor.getLong(cursor.getColumnIndex(DbTaskConst.ID)),
                        positionWork = cursor.getInt(cursor.getColumnIndex(DbTaskConst.POSITION_WORK)),
                        positionCompleted = cursor.getInt(cursor.getColumnIndex(DbTaskConst.POSITION_COMPLETED)),
                        title = cursor.getString(cursor.getColumnIndex(DbTaskConst.TITLE)),
                        info = cursor.getString(cursor.getColumnIndex(DbTaskConst.INFO)),
                        isCompleted = cursor.getInt(cursor.getColumnIndex(DbTaskConst.IS_COMPLETED)) > 0,
                        alarmAt = Date(cursor.getLong(cursor.getColumnIndex(DbTaskConst.ALARM_AT)))
                    )
                )
            }
        }
        return list
    }

    override fun deleteTaskById(id: Long): Int {
        return db.delete(
            DbTaskConst.TABLE,
            "${DbTaskConst.ID} = ? ",
            arrayOf(id.toString())
        )
    }

    override fun updateTask(task: TaskEntity): Int {
        val contentValues = ContentValues()
        contentValues.apply {
            put(DbTaskConst.POSITION_WORK, task.positionWork)
            put(DbTaskConst.POSITION_COMPLETED, task.positionCompleted)
            put(DbTaskConst.TITLE, task.title)
            put(DbTaskConst.INFO, task.info)
            put(DbTaskConst.IS_COMPLETED, task.isCompleted)
            put(DbTaskConst.ALARM_AT, task.alarmAt.time)
        }

        return db.update(
            DbTaskConst.TABLE,
            contentValues,
            "${DbTaskConst.ID} = ? ",
            arrayOf(task.id.toString())
        )

    }


}

