package com.example.education.utils.extensions

import android.database.DatabaseUtils

fun String?.toSqlLike(): String {
    return if (isNullOrBlank()) "null" else DatabaseUtils.sqlEscapeString("%${this}%")
}