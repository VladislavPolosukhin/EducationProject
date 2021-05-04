package com.example.education.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity

abstract class AbstrStatusTaskEntity: RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

   open fun updateData (list: List<TaskEntity>){}
}