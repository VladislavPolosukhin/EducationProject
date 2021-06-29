package com.example.education

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {


    override fun doWork(): Result {

        print("worker started")

        print("param1 = ${inputData.getInt("param1", 0)}")
        print("param2 = ${inputData.getString("param2")}")

        return Result.success()
    }

}