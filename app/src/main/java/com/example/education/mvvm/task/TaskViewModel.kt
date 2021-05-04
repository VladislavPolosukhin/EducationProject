package com.example.education.mvvm.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.education.data.TaskEntity
import com.example.education.mvvm.BaseViewModel
import com.example.education.repository.TaskDataSourceFactory
import com.example.education.repository.TaskRepository
import com.example.education.useCase.LoadTasksUseCase
import com.example.education.useCase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.inject
import java.util.*
import java.util.concurrent.Executors

class TaskViewModel : BaseViewModel() {

    private val taskRepo: TaskRepository by inject()
    val loading = MutableLiveData<Boolean>()
    val updateContent = MutableLiveData<List<TaskEntity>>()
    var isCompleted: Boolean = false

    private val dataSourceFactory = TaskDataSourceFactory(taskRepo = taskRepo)
    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(3)
        .build()
    val pagedList = LivePagedListBuilder(dataSourceFactory, config)
        .setInitialLoadKey(0)
        .setFetchExecutor(Executors.newSingleThreadExecutor())
        .build()

    fun loadTasks(isCompleted: Boolean) {
        this.isCompleted = isCompleted
        viewModelScope.launch {
            loading.postValue(true)
            val tasksFromDb = withContext(Dispatchers.IO) {
                taskRepo.getByBoolean(isCompleted)
            }
            updateContent.postValue(tasksFromDb)
            loading.postValue(false)
        }
    }

    fun addTask() {
        viewModelScope.launch {
            val taskEntity = TaskEntity(
                id = 1,
                positionWork = 1,
                positionCompleted = 0,
                title = "worker",
                info = "cool guy",
                isCompleted = false,
                alarmAt = Date()
            )
            withContext(Dispatchers.IO) {
                taskRepo.insertTask(taskEntity)
            }
            loadTasks(isCompleted)
        }
    }
}