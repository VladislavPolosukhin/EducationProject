package com.example.education.repository

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.education.const.DbTaskConst
import com.example.education.data.TaskEntity
import com.example.education.repository.mapper.CursorToTaskEntityMapper
import com.example.education.repository.mapper.toContentValues
import com.example.education.repository.query.taskRepo.SelectCountTask
import com.example.education.repository.query.taskRepo.SelectTaskById
import com.example.education.repository.query.taskRepo.SelectTasksByAlarms
import com.example.education.repository.query.taskRepo.SelectTasksByPageSql
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

    fun getById(id: Long): TaskEntity?
    // TODO добавить getById(id: Long): TaskEntity?

    fun getByAlarm(date: Date): List<TaskEntity>
    // TODO добавить getByAlarm(date: Date()): List<TaskEntity>

    fun getCount(isCompleted: Boolean): Int
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
       /* val list : MutableList<TaskEntity> = QueryExecutor.getList(
            db,
            SelectCountTask(),
            arrayOf(1.toString()),
           // arrayOf((if (isCompleted) 1 else 0).toString()),
            CursorToTaskEntityMapper
        )
        Log.d("MyTag", list.toString())*/
        return QueryExecutor.getCount(
            db,
            SelectCountTask(),
            arrayOf((if (isCompleted) 1 else 0).toString())
        )
    }


}

