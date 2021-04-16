package com.example.education.utils

import android.database.Cursor

interface CursorToDataMapper<To> {
    fun map(cursor: Cursor): To
}