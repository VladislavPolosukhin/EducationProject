package com.example.education.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.education.const.DbTaskConst

class DbHelper(
    context: Context,
) : SQLiteOpenHelper(context, "my_table", null, 1) {

    private val createTask = """|CREATE TABLE ${DbTaskConst.TABLE} (
        |   ${DbTaskConst.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        |   ${DbTaskConst.POSITION_WORK} INTEGER DEFAULT 0,
        |   ${DbTaskConst.POSITION_COMPLETED} INTEGER DEFAULT 0,
        |   ${DbTaskConst.TITLE} TEXT DEFAULT 'N/A',
        |   ${DbTaskConst.INFO} TEXT DEFAULT '',
        |   ${DbTaskConst.IS_COMPLETED} INTEGER DEFAULT 0,
        |   ${DbTaskConst.ALARM_AT} INTEGER
        |);
    """.trimMargin()


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTask)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}