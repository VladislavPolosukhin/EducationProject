package com.example.education.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.R
import com.example.education.baseModels.BaseFragment
import com.example.education.databinding.ViewPager2FragmentLayoutBinding

class ViewPagerFragment : BaseFragment() {
    private val viewModel: ViewPagerModel by lazy { ViewModelProvider(this).get(ViewPagerModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ViewPager2FragmentLayoutBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.view_pager2_fragment_layout,
                container,
                false
            )
        binding.viewPager2.adapter = viewModel.pagerAdapter

        return binding.root
    }
}