package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.databinding.RecyclerItemsLayoutBinding

class CompletedTaskEntityAdapter : RecyclerView.Adapter<CompletedTaskEntityAdapter.MyHolder>() {

    private val taskList: MutableList<TaskEntity> = mutableListOf()

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RecyclerItemsLayoutBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemsLayoutBinding.inflate(inflater, parent, false)
        return MyHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val task = taskList[position]
        holder.binding?.task = task
    }

    override fun getItemCount(): Int = taskList.size

    fun updateData(newList: List<TaskEntity>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

}

