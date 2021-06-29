package com.example.education

import android.app.Application
import com.example.education.database.DbHelper
import com.example.education.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    // TODO создать UseCase для изменения позиций item-ов после их перетаскивания в рекуклере (в методе onMove)
    // TODO создать другой рекуклер с paging Library для InProgress - done
    // TODO переименовать ..AbstrStatusTaskEntity и переделать его под PagedAdapter - done

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(DataModule.dataModule)
        }

    }

}