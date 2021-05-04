package com.example.education.useCase

abstract class UseCase<out Type, in Params> {

    abstract fun run(params: Params): Type

    object None

}