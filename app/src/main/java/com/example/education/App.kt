package com.example.education

import android.app.Application
import com.example.education.database.DbHelper

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        instance = this
        val dbHelper = DbHelper(instance.applicationContext)
    }


    companion object {
        lateinit var instance: App
    }
}