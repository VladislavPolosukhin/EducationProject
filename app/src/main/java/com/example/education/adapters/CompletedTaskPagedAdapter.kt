package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.databinding.RecyclerItemsLayoutBinding

class CompletedTaskPagedAdapter(
    diffCallback: DiffUtil.ItemCallback<TaskEntity>
) : AbstractAdapterStatusTaskEntity(diffCallback) {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RecyclerItemsLayoutBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemsLayoutBinding.inflate(inflater, parent, false)
        return MyHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val taskEntity = getItem(position)
        holder as MyHolder
        holder.binding?.task = taskEntity
    }


}