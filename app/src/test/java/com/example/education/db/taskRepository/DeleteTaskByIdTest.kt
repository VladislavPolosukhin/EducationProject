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

class DeleteTaskByIdTest : AndroidBaseUnitTest() {

    private val taskEntity = TaskEntity(
        id = 7,
        positionWork = 1,
        positionCompleted = 0,
        title = "worker",
        info = "cool_guy",
        isCompleted = true,
        Date()
    )

    override fun beforePreparation() {}

    override fun afterPreparation() {}

    @Test
    fun `when db empty then return 0`() {
        val deletedCountOfRows = taskRepository.deleteTaskById(1)
        Assert.assertEquals(0, deletedCountOfRows)
    }


    @Test
    fun `when delete then return 1`() {
        val id = taskRepository.insertTask(taskEntity)
        val deletedCountOfRows = taskRepository.deleteTaskById(id)
        Assert.assertEquals(1, deletedCountOfRows)
    }

    @Test
    fun `when delete by unknown id then return 0`() {
        val id = taskRepository.insertTask(taskEntity)
        val deletedCountOfRows = taskRepository.deleteTaskById(999)
        Assert.assertEquals(0, deletedCountOfRows)
    }

}