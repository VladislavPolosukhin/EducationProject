package com.example.education.mvvm.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.R
import com.example.education.mvvm.BaseFragment
import com.example.education.databinding.FragmentTaskBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TaskFragment : BaseFragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private lateinit var binding: FragmentTaskBinding
    private val viewModel by lazy { ViewModelProvider(this).get(TaskViewModel::class.java) }

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
        binding.rvTask.adapter = viewModel.recyclerAdapter
        /*launch {
            val myList = getDataFromBase()
            println("печатаем в графическом потоке ${myList.first().info}")

        }*/

    }

    /*suspend fun getDataFromBase(): List<TaskEntity> {
        val dbHelper = DbHelper(requireContext())
        val taskRepo: TaskRepository = TaskRepositoryImpl(dbHelper.writableDatabase)
        //return taskRepo.getTasksByPage(0, 100)
        return withContext(Dispatchers.IO) {
            println("печатаем из IO")
            taskRepo.getTasksByPage(0, 100)
        }
    }*/

}