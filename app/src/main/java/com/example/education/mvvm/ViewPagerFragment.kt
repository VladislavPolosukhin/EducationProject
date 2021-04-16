package com.example.education.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.education.R
import com.example.education.adapters.ViewPagerAdapter
import com.example.education.databinding.ViewPager2FragmentLayoutBinding

class ViewPagerFragment : BaseFragment() {

    private val pagerAdapter by lazy { ViewPagerAdapter(childFragmentManager, lifecycle) }
    private val viewModel: ViewPagerViewModel by lazy {
        ViewModelProvider(this).get(ViewPagerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ViewPager2FragmentLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_pager2_fragment_layout,
            container,
            false
        )

        binding.viewPager2.adapter = pagerAdapter

        return binding.root
    }
}