package com.example.education.mvvm.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.education.R
import com.example.education.adapters.*
import com.example.education.databinding.FragmentTaskBinding
import com.example.education.mvvm.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TaskFragment : BaseFragment(), CoroutineScope {

    companion object {
        const val IS_COMPLETED_ARG = "IS_COMPLETED_ARG"
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private lateinit var binding: FragmentTaskBinding
    private val viewModel by lazy { ViewModelProvider(this).get(TaskViewModel::class.java) }

    //private lateinit var adapterCompleted: AbstrStatusTaskEntity
    private val adapterCompleted: CompletedTaskPagedAdapter by lazy {
        CompletedTaskPagedAdapter(TaskAdapterDiffUtilCallback())
    }
    private var isCompleted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        isCompleted = bundle?.getBoolean(IS_COMPLETED_ARG) ?: false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_task,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        if (isCompleted) {
            //adapterCompleted = CompletedTaskAdapter()
        } else {
            ProgressTaskEntity()
        }
        //val callback = SimpleItemTouchHelperCallback(adapterCompleted)
        //val touchHelper = ItemTouchHelper(callback)
        //touchHelper.attachToRecyclerView(binding.rvTask)

        binding.rvTask.adapter = adapterCompleted
        viewModel.loadTasks(isCompleted)
        observeLiveData()
    }

    private fun observeLiveData() {
        /*viewModel.updateContent.observe(viewLifecycleOwner) { taskList ->
            adapterCompleted.updateData(taskList)
        }*/
        viewModel.pagedList.observe(viewLifecycleOwner) { taskEntityPagedList ->
            adapterCompleted.submitList(taskEntityPagedList)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            Toast.makeText(
                requireContext(),
                "Загрузка ${if (!isLoading) "завершена" else "началась"}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}