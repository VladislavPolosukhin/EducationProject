package com.example.education.adapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.education.mvvm.task.TaskFragment


class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private var bundleTrue: Bundle = Bundle()
    private var bundleFalse: Bundle = Bundle()

    fun setBundles(list: List<Bundle>){
        bundleTrue = list[0]
        bundleFalse = list[1]
    }

    private val pages = listOf(
        TaskFragment(),
        TaskFragment()
    )

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = pages[position].apply {
        if (position == 1) arguments = bundleTrue else bundleFalse
    }


}