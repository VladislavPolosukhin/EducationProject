package com.example.education.repository

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.education.data.TaskEntity

class TaskDataSourceFactory(
    private val taskRepo: TaskRepository,
) : DataSource.Factory<Int, TaskEntity>() {

    override fun create(): DataSource<Int, TaskEntity> {

        return TaskPositionalDataSource()

    }

    /* private inner class TaskKeyedDataSource : PageKeyedDataSource<Boolean, TaskEntity>() {
         override fun loadInitial(
             params: LoadInitialParams<Boolean>,
             callback: LoadInitialCallback<Boolean, TaskEntity>
         ) {
             callback.onResult(
                 taskRepo.getByBoolean(isCompletedKey),
                 null,
                 isCompletedKey
             )
         }

         override fun loadBefore(
             params: LoadParams<Boolean>,
             callback: LoadCallback<Boolean, TaskEntity>
         ) {

         }

         override fun loadAfter(
             params: LoadParams<Boolean>,
             callback: LoadCallback<Boolean, TaskEntity>
         ) {
             callback.onResult(taskRepo.getByBoolean(params.key), params.key)
         }
     }*/

    private inner class TaskPositionalDataSource : PositionalDataSource<TaskEntity>() {

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<TaskEntity>
        ) {
            callback.onResult(
                taskRepo.getTasksByPage(
                    fromPosition = params.requestedStartPosition,
                    count = params.requestedLoadSize,
                    filter = ""
                ), params.requestedStartPosition
            )

        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TaskEntity>) {
            callback.onResult(
                taskRepo.getTasksByPage(
                    fromPosition = params.startPosition,
                    count = params.loadSize,
                    filter = ""
                )
            )

        }

    }

}