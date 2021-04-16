package com.example.education.repository

import android.database.sqlite.SQLiteDatabase
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import com.example.education.repository.mapper.CursorToTaskEntityMapper
import com.example.education.repository.mapper.toContentValues
import com.example.education.repository.query.taskRepo.SelectTasksByPageSql
import com.example.education.utils.QueryExecutor

interface TaskRepository {

    // Tested
    fun insertTask(task: TaskEntity): Long

    // Tested
    fun getTasksByPage(fromPosition: Int, count: Int, filter: String = ""): List<TaskEntity>

    // Tested
    fun deleteTaskById(id: Long): Int

    // Tested
    fun updateTask(task: TaskEntity): Int
    // TODO добавить getById(id: Long): TaskEntity?
    // TODO добавить getByAlarm(date: Date()): List<TaskEntity>
    // TODO добавить getCount(isCompleted: Boolean): Int

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


}

