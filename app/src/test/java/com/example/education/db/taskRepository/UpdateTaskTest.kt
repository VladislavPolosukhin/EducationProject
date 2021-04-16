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

class UpdateTaskTest : AndroidBaseUnitTest() {

    private val taskEntity = TaskEntity(
        id = 7,
        positionWork = 1,
        positionCompleted = 0,
        title = "worker",
        info = "cool_guy",
        isCompleted = true,
        alarmAt = Date()
    )

    override fun beforePreparation() {
        val dbHelper = DbHelper(ApplicationProvider.getApplicationContext())
        taskRepository = TaskRepositoryImpl(dbHelper.writableDatabase)
    }

    override fun afterPreparation() {}

    private lateinit var taskRepository: TaskRepository

    @Test
    fun `when db empty then return 0`() {
        val updatedCountOfRows = taskRepository.updateTask(taskEntity)
        Assert.assertEquals(0, updatedCountOfRows)
    }

    @Test
    fun `when update then return 1`() {
        val id = taskRepository.insertTask(taskEntity)
        val updatedCountOfRows = taskRepository.updateTask(taskEntity.copy(id = id))
        Assert.assertEquals(1, updatedCountOfRows)
    }

    @Test
    fun `when update unknown task then return 0`() {
        val id = taskRepository.insertTask(taskEntity)
        val updatedCountOfRows = taskRepository.updateTask(taskEntity.copy(id = 999))
        Assert.assertEquals(0, updatedCountOfRows)
    }

}
