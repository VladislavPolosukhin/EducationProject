package com.example.education.db.taskRepository

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.education.baseModels.App
import com.example.education.data.TaskEntity
import com.example.education.database.DbHelper
import com.example.education.database.TaskRepository
import com.example.education.database.TaskRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(
    application = App::class,
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.N_MR1]
)
class DeleteTaskByIdTest {

    private val listTasks = listOf<TaskEntity>(
        TaskEntity(
            id = 7,
            positionWork = 1,
            positionCompleted = 0,
            title = "worker",
            info = "cool_guy",
            isCompleted = true,
            Date()
        ),
        TaskEntity(
            1, 2, 1,
            "deputy_director", "not_a_cool_guy", true, Date()
        )
    )


    private lateinit var taskRepository: TaskRepository

    @Before
    fun before() {
        val dbHelper = DbHelper(ApplicationProvider.getApplicationContext())
        taskRepository = TaskRepositoryImpl(dbHelper.writableDatabase)
        listTasks.forEach { taskRepository.insertTask(it) }
    }

    @Test
    fun `check delete`() {
        val deletedCountOfRows = taskRepository.deleteTaskById(listTasks[1].id)
        Assert.assertEquals(1, deletedCountOfRows)
    }
}