package com.example.education.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.education.data.TaskEntity

class TaskAdapterDiffUtilCallback : DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.info == newItem.info &&
                oldItem.positionWork == newItem.positionWork &&
                oldItem.positionCompleted == newItem.positionCompleted &&
                oldItem.isCompleted == newItem.isCompleted &&
                oldItem.alarmAt == newItem.alarmAt
    }


}