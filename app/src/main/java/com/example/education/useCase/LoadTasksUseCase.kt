package com.example.education.useCase

import com.example.education.data.TaskEntity
import com.example.education.repository.TaskRepository

class LoadTasksUseCase(
    private val taskRepository: TaskRepository
) : UseCase<List<TaskEntity>, UseCase.None>() {

    override fun run(params: None): List<TaskEntity> {
        val oldList = taskRepository.getTasksByPage(0, Int.MAX_VALUE, "")

        oldList.forEach { task ->
            taskRepository.deleteTaskById(task.id)
        }

        oldList.forEach { task ->
            taskRepository.insertTask(task)
        }

        return taskRepository.getTasksByPage(0, Int.MAX_VALUE, "")
    }

}