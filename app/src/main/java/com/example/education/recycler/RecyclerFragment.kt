package com.example.education.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.baseModels.BaseFragment
import com.example.education.R
import com.example.education.databinding.RecyclerFragmentLayoutBinding

class RecyclerFragment : BaseFragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(RecyclerViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: RecyclerFragmentLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.recycler_fragment_layout, container, false)
        binding.viewModelRF = viewModel

        return binding.root
    }
}