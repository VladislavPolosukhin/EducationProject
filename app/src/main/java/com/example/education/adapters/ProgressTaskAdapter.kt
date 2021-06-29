package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.databinding.ProgressTaskItemsRecyclerViewBinding
import com.example.education.databinding.RecyclerItemsLayoutBinding

class ProgressTaskAdapter(diffCallBack: DiffUtil.ItemCallback<TaskEntity>) :
    AbstractAdapterStatusTaskEntity(diffCallBack) {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RecyclerItemsLayoutBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProgressTaskItemsRecyclerViewBinding.inflate(inflater, parent, false)
        return MyHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val taskEntity = getItem(position)
        holder as MyHolder
        holder.binding?.task = taskEntity
    }


}