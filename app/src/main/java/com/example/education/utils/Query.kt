package com.example.education.utils

interface Query {
    fun getQuery(): String
    fun getQueryThroughId (): String
    fun getQueryThroughDate (): String
}