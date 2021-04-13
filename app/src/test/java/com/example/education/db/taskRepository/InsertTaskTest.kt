package com.example.education.db.taskRepository

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.education.baseModels.App
import com.example.education.data.TaskEntity
import com.example.education.database.DbHelper
import com.example.education.database.TaskRepository
import com.example.education.database.TaskRepositoryImpl
import org.junit.After
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
class InsertTaskTest {

    private val task = TaskEntity(
        id = 0,
        positionWork = 0,
        positionCompleted = 0,
        title = "title",
        info = "info",
        isCompleted = false,
        alarmAt = Date()
    )
    private lateinit var taskRepo: TaskRepository


    @Before
    fun before() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val dbHelper = DbHelper(context)
        val database = dbHelper.writableDatabase
        taskRepo = TaskRepositoryImpl(database)
    }

    @After
    fun after() {

    }

    @Test
    fun `when insert then return not -1`() {
        val returnedRowId = taskRepo.insertTask(task)
        Assert.assertNotEquals(-1L, returnedRowId)
    }

    @Test
    fun `when insert then read return list with same item`() {
        val returnedRowId = taskRepo.insertTask(task)
        val expectedEntity = task.copy(id = returnedRowId)
        val list = taskRepo.getTasksByPage(0, 100)

        Assert.assertEquals(expectedEntity, list[0])
    }

}