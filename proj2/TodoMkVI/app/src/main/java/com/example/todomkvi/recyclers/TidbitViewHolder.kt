package com.example.todomkvi.recyclers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todomkvi.R
import com.example.todomkvi.data.Tidbit

class TidbitViewHolder (v: View) :RecyclerView.ViewHolder (v) {
    fun bind(t: Tidbit) {
        val title = itemView.findViewById<TextView>(R.id.tv_rvcard_tidbit_simple_title)
        title.text = t.title
    }
}