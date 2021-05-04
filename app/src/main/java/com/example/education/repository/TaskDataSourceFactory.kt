package com.example.education.repository

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.education.data.TaskEntity

class TaskDataSourceFactory(
    private val taskRepo: TaskRepository
) : DataSource.Factory<Int, TaskEntity>() {

    override fun create(): DataSource<Int, TaskEntity> {
        return TaskPositionalDataSource()
    }

    private inner class TaskPositionalDataSource : PositionalDataSource<TaskEntity>() {

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<TaskEntity>
        ) {
            taskRepo.getTasksByPage(
                fromPosition = params.requestedStartPosition,
                count = params.requestedLoadSize,
                filter = ""
            )
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TaskEntity>) {
            taskRepo.getTasksByPage(
                fromPosition = params.startPosition,
                count = params.loadSize,
                filter = ""
            )
        }

    }

}