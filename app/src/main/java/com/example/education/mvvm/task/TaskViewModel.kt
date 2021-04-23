package com.example.education.mvvm.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.education.data.TaskEntity
import com.example.education.mvvm.BaseViewModel
import com.example.education.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class TaskViewModel : BaseViewModel() {

    val loading = MutableLiveData<Boolean>()
    val updateContent = MutableLiveData<List<TaskEntity>>()

    private val taskRepo: TaskRepository by inject()

    fun loadTasks() {
        viewModelScope.launch {
            loading.postValue(true)

            val tasksFromDb = withContext(Dispatchers.IO) {
                taskRepo.getTasksByPage(
                    fromPosition = 0,
                    count = Int.MAX_VALUE,
                    filter = ""
                )
            }

            updateContent.postValue(tasksFromDb)
            loading.postValue(false)
        }
    }

}