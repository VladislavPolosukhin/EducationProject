package com.example.education.mvvm

import com.example.education.baseModels.BaseViewModel
import com.example.education.adapters.MyRecycler

class RecyclerViewModel : BaseViewModel() {
    val name = "recycler fragment"

    val recyclerAdapter = MyRecycler(arrayListOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "You"))

}