package com.example.education.mvvm

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.work.*
import com.example.education.MyWorker
import com.example.education.R
import com.example.education.database.DbHelper
import com.example.education.generated.callback.OnClickListener
import com.example.education.repository.TaskRepository
import com.example.education.repository.TaskRepositoryImpl
import kotlinx.android.synthetic.main.activity_main_layout.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    //val taskRepo: TaskRepository by inject()

    interface TestInter {
        fun testFun(num: Int)
    }

    val testLam: (Int) -> Unit = {}

    val testLam2 = fun(num2: Int) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

        startWorker()


        val testInter = object : TestInter {
            override fun testFun(num: Int) {

            }
        }

        testInter.testFun(1)
        testLam(2)
        testLam2(3)

        val listener = object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        }


    }

    private fun handlerExample() {
        val handler = Handler()

        handler.postDelayed(
            {
                println("handler activated")
            },
            1000
        )

        handler.sendEmptyMessage(1)
    }

    private fun startWorker() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .addTag("specialTag")
            .setConstraints(constraint)
            .setInputData(
                Data.Builder()
                    .putInt("param1", 1)
                    .putString("param2", "2")
                    .build()
            )
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        val workId = workRequest.id

        //WorkManager.getInstance(this).cancelWorkById(workId)

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workId)
            .observeForever { workInfo ->
                println("work status: ${workInfo.state}")
            }
    }

}