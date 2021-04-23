package com.example.education.mvvm

import android.os.Bundle
import com.example.education.R
import com.example.education.database.DbHelper
import com.example.education.repository.TaskRepository
import com.example.education.repository.TaskRepositoryImpl
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    //val taskRepo: TaskRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

    }

}