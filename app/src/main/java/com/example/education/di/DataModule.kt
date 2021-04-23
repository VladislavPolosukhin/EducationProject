package com.example.education.di

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.education.database.DbHelper
import com.example.education.repository.TaskRepository
import com.example.education.repository.TaskRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DataModule {

    val dataModule = module {

        fun provideDataBase(context: Context): SQLiteDatabase {
            return DbHelper(context).writableDatabase
        }

        factory { provideDataBase(androidApplication().applicationContext) }

        fun provideTaskRepo(db: SQLiteDatabase): TaskRepository {
            return TaskRepositoryImpl(db)
        }

        single { provideTaskRepo(get()) }

    }

}