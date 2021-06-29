package com.example.education.adapters

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.education.data.TaskEntity
import com.example.education.repository.TaskRepository
import com.example.education.useCase.UpdateTaskUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

abstract class AbstractAdapterStatusTaskEntity(diffCallBack: DiffUtil.ItemCallback<TaskEntity>) :
    PagedListAdapter<TaskEntity, RecyclerView.ViewHolder>(diffCallBack), ItemTouchHelperAdapter, KoinComponent {

    private val taskRepo: TaskRepository by inject()

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val taskEntity =  getItem(fromPosition)
        taskEntity?.positionWork = toPosition
        taskEntity?.let { UpdateTaskUseCase(taskRepo).run(it) }

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {

                Collections.swap(currentList!!.toMutableList(), i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(currentList!!.toMutableList(), i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
}