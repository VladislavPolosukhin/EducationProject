package com.example.education.db.taskRepository

import androidx.test.core.app.ApplicationProvider
import com.example.education.AndroidBaseUnitTest
import com.example.education.data.TaskEntity
import com.example.education.database.DbHelper
import com.example.education.repository.TaskRepository
import com.example.education.repository.TaskRepositoryImpl
import org.junit.Assert
import org.junit.Test
import java.util.*

class GetByAlarmTaskTest : AndroidBaseUnitTest() {

    private val task = TaskEntity(
        id = 1,
        positionWork = 1,
        positionCompleted = 0,
        title = "worker",
        info = "cool guy",
        isCompleted = true,
        alarmAt = Date()
    )

    val date = Date()

    private lateinit var taskRepository: TaskRepository

    override fun beforePreparation() {
        val dbHelper = DbHelper(ApplicationProvider.getApplicationContext())
        taskRepository = TaskRepositoryImpl(dbHelper.writableDatabase)
    }

    override fun afterPreparation() {}

    @Test
    fun `when take by date` (){
        taskRepository.insertTask(task)
        val taskList = taskRepository.getByAlarm(date)
        Assert.assertEquals(task, taskList.first())
    }

}