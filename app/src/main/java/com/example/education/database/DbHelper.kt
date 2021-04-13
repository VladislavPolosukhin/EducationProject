package com.example.education.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.education.baseModels.App

class DbHelper(private val context: Context, val tableName: String = "my_table", val version: Int = 1) :
    SQLiteOpenHelper(context, tableName, null, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE task_entity (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " position_work INTEGER DEFAULT 0," +
                "position_completed INTEGER DEFAULT 0," +
                "title TEXT DEFAULT 'N/A'," +
                "info TEXT DEFAULT ''," +
                "is_completed INTEGER DEFAULT 0," +
                "alarm_at INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}