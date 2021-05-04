package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.databinding.RecyclerItemsLayoutBinding
import java.util.*

class CompletedTaskAdapter : AbstrStatusTaskEntity() {

    private val taskList: MutableList<TaskEntity> = mutableListOf()

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RecyclerItemsLayoutBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemsLayoutBinding.inflate(inflater, parent, false)
        return MyHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = taskList[position]
        holder as MyHolder
        holder.binding?.task = task
    }


    override fun getItemCount(): Int = taskList.size

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

