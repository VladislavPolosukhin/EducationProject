package com.example.education.useCase

import com.example.education.AndroidBaseUnitTest
import com.example.education.data.TaskEntity
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test
import java.util.*

class LoadTasksUseCaseTest : AndroidBaseUnitTest() {

    override fun beforePreparation() {}

    override fun afterPreparation() {}

    @Test
    fun asd() {
        runBlocking {


            var a = 0
            launch {
                withContext(Dispatchers.IO) {
                    a = 543
                    // ждем
                    // ждем
                    // ждем
                    // ждем
                    // ждем
                }

                launch {
                    a = 5
                }
                assert(a == 543)
            }

        }

    }

    @Test
    fun `when run then return same`() {
        taskRepository.insertTask(
            TaskEntity(
                id = 1,
                positionWork = 1,
                positionCompleted = 2,
                title = "sdasd",
                info = "info",
                isCompleted = false,
                alarmAt = Date()
            )
        )

        val list = LoadTasksUseCase(taskRepository).run(UseCase.None)
        Assert.assertEquals(1, list.size)


    }

}