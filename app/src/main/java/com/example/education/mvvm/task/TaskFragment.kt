package com.example.education.mvvm.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.R
import com.example.education.adapters.CompletedTaskEntityAdapter
import com.example.education.databinding.FragmentTaskBinding
import com.example.education.mvvm.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TaskFragment : BaseFragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private lateinit var binding: FragmentTaskBinding
    private val viewModel by lazy { ViewModelProvider(this).get(TaskViewModel::class.java) }
    private val adapterCompleted: CompletedTaskEntityAdapter by lazy { CompletedTaskEntityAdapter() }

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
        binding.rvTask.adapter = adapterCompleted
        viewModel.loadTasks()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.updateContent.observe(viewLifecycleOwner) { taskList ->
            adapterCompleted.updateData(taskList)
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