package com.example.education

import android.app.Application
import com.example.education.database.DbHelper
import com.example.education.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(DataModule.dataModule)
        }

    }

}