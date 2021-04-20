package com.example.education.utils

import android.database.sqlite.SQLiteDatabase

object QueryExecutor {

    fun <T> getList(
        db: SQLiteDatabase,
        sql: Query,
        selectionArgs: Array<String>?,
        cursorToDataMapper: CursorToDataMapper<T>
    ): MutableList<T> {
        val list = mutableListOf<T>()
        db.rawQuery(sql.getQuery(), selectionArgs).use {
            while (it.moveToNext()) {
                list.add(cursorToDataMapper.map(it))
            }
        }
        return list
    }

    fun <T> getItem(
        db: SQLiteDatabase,
        sql: Query,
        selectionArgs: Array<String>?,
        cursorToDataMapper: CursorToDataMapper<T>
    ): T? {
        var item: T? = null
        db.rawQuery(sql.getQuery(), selectionArgs).use {
            if (it.moveToFirst()) {
                item = cursorToDataMapper.map(it)
            }
        }
        return item
    }

    fun getCount(
        db: SQLiteDatabase,
        sql: Query,
        selectionArgs: Array<String>?
    ): Int {
        var count: Int = 0
        val cursor = db.rawQuery(sql.getQuery(), selectionArgs).use {
            if (it.moveToFirst()) {
                count = it.getInt(0)
            }
        }

        return count
    }

}