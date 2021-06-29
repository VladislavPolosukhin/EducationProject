package com.example.education.useCase
import com.example.education.repository.TaskRepository

import org.koin.core.component.inject


abstract class UseCase<out Type, in Params> {

    abstract fun run(params: Params): Type

    object None

}