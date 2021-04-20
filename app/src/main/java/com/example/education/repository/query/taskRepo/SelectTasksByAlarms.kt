package com.example.education.repository.query.taskRepo

import com.example.education.const.DbTaskConst
import com.example.education.utils.Query

class SelectTasksByAlarms : Query {

    override fun getQuery(): String {
        return """|SELECT
            |*  
            |FROM ${DbTaskConst.TABLE}
            |WHERE ${DbTaskConst.ALARM_AT}
            |= ? 
            """.trimMargin()
    }
}