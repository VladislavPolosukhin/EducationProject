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

class GetTasksByPageTest : AndroidBaseUnitTest() {

    private val task = TaskEntity(
        id = 1,
        positionWork = 1,
        positionCompleted = 0,
        title = "worker",
        info = "cool guy",
        isCompleted = true,
        alarmAt = Date()
    )

    private lateinit var taskRepository: TaskRepository

    override fun beforePreparation() {
        val dbHelper = DbHelper(ApplicationProvider.getApplicationContext())
        taskRepository = TaskRepositoryImpl(dbHelper.writableDatabase)
    }

    override fun afterPreparation() {}

    @Test
    fun `when db empty then return empty list`() {
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 0,
            count = 100,
            filter = ""
        )

        Assert.assertTrue(taskList.isEmpty())
    }

    @Test
    fun `when contains 1 task then return 1`() {
        taskRepository.insertTask(task)
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 0,
            count = 100,
            filter = ""
        )

        Assert.assertEquals(1, taskList.size)
    }

    @Test
    fun `when contains 10 tasks then return 10`() {
        repeat(10) {
            taskRepository.insertTask(task)
        }
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 0,
            count = 100,
            filter = ""
        )

        Assert.assertEquals(10, taskList.size)
    }

    @Test
    fun `when contains 10 tasks then get by filter=title_1 return 1`() {
        repeat(10) { iteration ->
            taskRepository.insertTask(
                task.copy(title = "title_${iteration}")
            )
        }
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 0,
            count = 100,
            filter = "title_1"
        )

        Assert.assertEquals(1, taskList.size)
    }

    @Test
    fun `when contains 10 tasks then get by fromPosition=5 return 5`() {
        repeat(10) {
            taskRepository.insertTask(task)
        }
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 5,
            count = 100,
            filter = ""
        )

        Assert.assertEquals(5, taskList.size)
    }

    @Test
    fun `when contains 10 tasks then get by count=2 return 2`() {
        repeat(10) {
            taskRepository.insertTask(task)
        }
        val taskList = taskRepository.getTasksByPage(
            fromPosition = 0,
            count = 2,
            filter = ""
        )

        Assert.assertEquals(2, taskList.size)
    }

}