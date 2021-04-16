package com.example.education.database

import android.database.sqlite.SQLiteDatabase
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import java.util.*

interface TaskRepository {

    fun insertTask(task: TaskEntity): Long
    fun getTasksByPage(fromPosition: Int, count: Int): List<TaskEntity>
    fun deleteTaskById(id: Long): Int
    fun updateTask(task: TaskEntity): Int

}

class TaskRepositoryImpl(
    private val db: SQLiteDatabase
) : TaskRepository {

    override fun insertTask(task: TaskEntity): Long {
        return db.insertOrThrow(
            DbTaskConst.TABLE,
            null,
            CursorParser.getContentValues(task)
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
                   CursorParser.getTaskEntity(cursor)
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
        return db.update(
            DbTaskConst.TABLE,
            CursorParser.getContentValues(task),
            "${DbTaskConst.ID} = ? ",
            arrayOf(task.id.toString())
        )

    }


}

