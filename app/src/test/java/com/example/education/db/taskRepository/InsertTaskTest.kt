package com.example.education.db.taskRepository

import com.example.education.AndroidBaseUnitTest
import com.example.education.data.TaskEntity
import com.example.education.repository.TaskRepository
import org.junit.Assert
import org.junit.Test
import java.util.*

class InsertTaskTest : AndroidBaseUnitTest() {

    private val task = TaskEntity(
        id = 0,
        positionWork = 0,
        positionCompleted = 0,
        title = "title",
        info = "info",
        isCompleted = false,
        alarmAt = Date()
    )


    override fun beforePreparation() {}

    override fun afterPreparation() {}

    @Test
    fun `when insert then return not -1`() {
        val returnedRowId = taskRepository.insertTask(task)
        Assert.assertNotEquals(-1L, returnedRowId)
    }

    @Test
    fun `when insert then read return list with same item`() {
        val returnedRowId = taskRepository.insertTask(task)
        val expectedEntity = task.copy(id = returnedRowId)
        val list = taskRepository.getTasksByPage(0, 100)

        Assert.assertEquals(expectedEntity, list[0])
    }

}