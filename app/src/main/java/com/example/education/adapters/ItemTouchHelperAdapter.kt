package com.example.education.adapters

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
}