package com.example.education.mvvm.task

import com.example.education.baseModels.BaseViewModel
import com.example.education.adapters.MyRecycler

class TaskViewModel : BaseViewModel() {


    val recyclerAdapter = MyRecycler(arrayListOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "You"))

}