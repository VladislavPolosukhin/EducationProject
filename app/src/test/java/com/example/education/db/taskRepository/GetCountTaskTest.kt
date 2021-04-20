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

class GetCountTaskTest : AndroidBaseUnitTest() {

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

    fun `check the quantity of inserted` (){
        taskRepository.insertTask(task)
        val quantityCompleted = taskRepository.getCount(true)
        Assert.assertEquals(1,quantityCompleted)
    }
}