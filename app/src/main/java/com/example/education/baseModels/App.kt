package com.example.education.baseModels

import android.app.Application
import com.example.education.database.DbHelper

class App : Application() {

    // TODO удалить RecyclerFragment
    // TODO сделать отдельные классы(имплементируемые от интерфейса) для query
    // TODO сделать парсеры/мапперы для считывания результата из Cursor

    override fun onCreate() {
        super.onCreate()
        instance = this
        val dbHelper = DbHelper(instance.applicationContext)
    }


    companion object {
        lateinit var instance: App
    }
}