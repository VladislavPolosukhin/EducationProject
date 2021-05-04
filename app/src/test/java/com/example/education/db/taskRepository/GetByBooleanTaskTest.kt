package com.example.education.db.taskRepository

import com.example.education.AndroidBaseUnitTest
import com.example.education.data.TaskEntity
import org.junit.Assert
import org.junit.Test
import java.util.*

class GetByBooleanTaskTest : AndroidBaseUnitTest() {

    override fun beforePreparation() {}

    override fun afterPreparation() {}

    private val task = TaskEntity(
        id = 1,
        positionWork = 1,
        positionCompleted = 0,
        title = "worker",
        info = "cool guy",
        isCompleted = false,
        alarmAt = Date()
    )

    @Test
    fun `when return by boolean`(){
        taskRepository.insertTask(task)
        val taskList = taskRepository.getByBoolean(false)
        Assert.assertEquals(task, taskList.first())
    }

}