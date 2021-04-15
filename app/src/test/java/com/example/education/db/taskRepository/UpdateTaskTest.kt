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
class UpdateTaskTest {

    private val listTasks = listOf<TaskEntity>(
        TaskEntity
            (
            7, 1, 0,
            "worker", "cool_guy", true, Date()
        ), TaskEntity
            (
            2, 2, 1,
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
    fun `check update`() {
        val id = taskRepository.updateTask(
            TaskEntity(0, 1, 0,
                "worker", "cool_guy", true, Date())
        )
        Assert.assertEquals(id, 0)
    }
}
