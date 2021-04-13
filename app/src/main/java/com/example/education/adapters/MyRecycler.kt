package com.example.education.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.education.R
import com.example.education.baseModels.App

class MyRecycler(
    private val dataList: List<String>
) : RecyclerView.Adapter<MyRecycler.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater: LayoutInflater = LayoutInflater.from(App.instance.applicationContext)
        val view: View = inflater.inflate(R.layout.recycler_items_layout, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textView.text = dataList[position]
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.recyclerTV)
    }

}

