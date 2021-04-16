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

// TODO ДЗ сравнить сами объекты (из бд с объектами из listTasks)

@RunWith(RobolectricTestRunner::class)
@Config(
    application = App::class,
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.N_MR1]
)

class GetTasksByPageTest {

    private val listTasks = listOf<TaskEntity>(
        TaskEntity
            (
            1, 1, 0,
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
        for (item in listTasks) {
            taskRepository.insertTask(item)
        }
    }

    @Test
    fun `when insert check quantity`() {
        taskRepository.deleteTaskById((listTasks[1].id))
        val list = taskRepository.getTasksByPage(0, 10)
        Assert.assertNotEquals(listTasks.size,list.size)
    }

    @Test
    fun `when insert check objects` (){
        val list = taskRepository.getTasksByPage(0,100)
        Assert.assertEquals(listTasks,list)
    }

}