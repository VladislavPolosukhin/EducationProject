package com.example.education.mvvm

import android.os.Bundle
import com.example.education.baseModels.BaseActivity
import com.example.education.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)
    }
}