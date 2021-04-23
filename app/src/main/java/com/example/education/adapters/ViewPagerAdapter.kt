package com.example.education.adapters

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

    private val pages = listOf(
        TaskFragment(), // FIXME Progress
        TaskFragment() // FIXME Completed
    )

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment = pages[position]


    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_pager2_items_layout, parent, false)
        )

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        tvTitle.text = "item $position"
        container.setBackgroundResource(colors[position])
    }*/
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)