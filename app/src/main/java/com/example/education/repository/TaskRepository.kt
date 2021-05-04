package com.example.education.repository

import android.database.sqlite.SQLiteDatabase
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import com.example.education.repository.mapper.CursorToTaskEntityMapper
import com.example.education.repository.mapper.toContentValues
import com.example.education.repository.query.taskRepo.*
import com.example.education.utils.QueryExecutor
import java.util.*

interface TaskRepository {

    // Tested
    fun insertTask(task: TaskEntity): Long

    // Tested
    fun getTasksByPage(fromPosition: Int, count: Int, filter: String = ""): List<TaskEntity>

    // Tested
    fun deleteTaskById(id: Long): Int

    // Tested
    fun updateTask(task: TaskEntity): Int

    // Tested
    fun getById(id: Long): TaskEntity?

    // Tested
    fun getByAlarm(date: Date): List<TaskEntity>

    // Tested
    fun getCount(isCompleted: Boolean): Int

    // Tested
    fun getByBoolean(isCompleted: Boolean): List<TaskEntity>

}

class TaskRepositoryImpl(
    private val db: SQLiteDatabase
) : TaskRepository {

    override fun insertTask(task: TaskEntity): Long {
        return db.insertOrThrow(
            DbTaskConst.TABLE,
            null,
            task.toContentValues()
        )
    }

    override fun getTasksByPage(fromPosition: Int, count: Int, filter: String): List<TaskEntity> {
        return QueryExecutor.getList(
            db,
            SelectTasksByPageSql(filter),
            arrayOf(/*filter, filter,*/ "$count", "$fromPosition"),
            CursorToTaskEntityMapper
        )
    }

    override fun deleteTaskById(id: Long): Int {
        return db.delete(
            DbTaskConst.TABLE,
            "${DbTaskConst.ID} = ?",
            arrayOf(id.toString())
        )
    }

    override fun updateTask(task: TaskEntity): Int {
        return db.update(
            DbTaskConst.TABLE,
            task.toContentValues(),
            "${DbTaskConst.ID} = ?",
            arrayOf("${task.id}")
        )
    }

    override fun getById(id: Long): TaskEntity? {
        return QueryExecutor.getItem(
            db = db,
            sql = SelectTaskById(),
            selectionArgs = arrayOf(id.toString()),
            cursorToDataMapper = CursorToTaskEntityMapper
        )

    }

    override fun getByAlarm(date: Date): List<TaskEntity> {
        return QueryExecutor.getList(
            db,
            SelectTasksByAlarms(),
            arrayOf(date.time.toString()),
            CursorToTaskEntityMapper
        )
    }

    override fun getCount(isCompleted: Boolean): Int {
        return QueryExecutor.getCount(
            db,
            SelectCountTask(),
            arrayOf((if (isCompleted) 1 else 0).toString())
        )
    }

    override fun getByBoolean(isCompleted: Boolean): List<TaskEntity> {
        return QueryExecutor.getList(
            db,
            SelectTaskByCoompleted(),
            arrayOf((if (isCompleted) 1 else 0).toString()),
            CursorToTaskEntityMapper
        )
    }


}

