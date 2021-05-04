package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.databinding.ProgressTaskItemsRecyclerViewBinding
import com.example.education.databinding.RecyclerItemsLayoutBinding
import java.util.*

class ProgressTaskEntity :  AbstrStatusTaskEntity() {

    private val taskList: MutableList<TaskEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressTaskEntityHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemsLayoutBinding.inflate(inflater, parent, false)
        return ProgressTaskEntityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ProgressTaskEntityHolder
        holder.binding?.task = taskList[position]
    }

    override fun getItemCount() = taskList.size

    //
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(taskList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(taskList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun updateData(list: List<TaskEntity>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

}

class ProgressTaskEntityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = DataBindingUtil.bind<ProgressTaskItemsRecyclerViewBinding>(itemView)
}