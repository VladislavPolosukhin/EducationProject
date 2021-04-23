package com.example.education

import android.app.Application
import com.example.education.database.DbHelper
import com.example.education.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    // TODO перенести тесты на Koin
    // TODO создать второй адаптер ProgressTaskEntityAdapter (на примере CompletedTaskEntityAdapter)
    // TODO Из ViewPagerAdapter передавать в bundle значение для Completed и Progress

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(DataModule.dataModule)
        }

    }

}