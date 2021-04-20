package com.example.education.repository.query.taskRepo

import com.example.education.const.DbTaskConst
import com.example.education.utils.Query

class SelectCountTask : Query {

    override fun getQuery(): String {
        return """|SELECT
            |COUNT (*)  
            |FROM ${DbTaskConst.TABLE}
            |WHERE ${DbTaskConst.IS_COMPLETED}
            |= ? 
            """.trimMargin()
    }
}