package com.example.education.repository.query.taskRepo

import com.example.education.const.DbTaskConst
import com.example.education.utils.Query
import com.example.education.utils.extensions.toSqlLike

class SelectTasksByPageSql(private val filter: String) : Query {

    override fun getQuery(): String {
        val whereClause = """|
            |   ${DbTaskConst.TITLE} LIKE ${filter.toSqlLike()}
            |   OR ${DbTaskConst.INFO} LIKE ${filter.toSqlLike()}
        """.trimMargin()

        return """|SELECT
            |    *
            |FROM ${DbTaskConst.TABLE}
            |${if (filter.isNotEmpty()) "WHERE $whereClause" else ""}
            |LIMIT ? OFFSET ?
         """.trimMargin()
    }

}