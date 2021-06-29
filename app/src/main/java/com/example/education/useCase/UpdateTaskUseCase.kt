package com.example.education.useCase

import com.example.education.data.TaskEntity
import com.example.education.repository.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository): UseCase<UseCase.None, TaskEntity>() {
    override fun run(params: TaskEntity): None {
        taskRepository.updateTask(params)
        return None
    }
}