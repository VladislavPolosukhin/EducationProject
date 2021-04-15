package com.example.education.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.baseModels.BaseFragment
import com.example.education.R
import com.example.education.databinding.MainFragmentLayoutBinding

class MainFragment : BaseFragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: MainFragmentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment_layout,container,false)
        binding.viewModelMF = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*viewModel.textData.observe(viewLifecycleOwner) { receivedData ->

        }*/

    }

}