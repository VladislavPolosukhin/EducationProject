package com.example.education.repository.query.taskRepo

import com.example.education.const.DbTaskConst
import com.example.education.utils.Query

class SelectTaskByCoompleted : Query {
    override fun getQuery(): String {
        return """|SELECT
            |*
            |FROM ${DbTaskConst.TABLE}
            |WHERE ${DbTaskConst.IS_COMPLETED} = ?
        """.trimMargin()
    }
}