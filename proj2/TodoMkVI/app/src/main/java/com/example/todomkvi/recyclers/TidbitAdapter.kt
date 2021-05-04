package com.example.todomkvi.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todomkvi.R
import com.example.todomkvi.data.Tidbit

class TidbitAdapter (val tidbits:ArrayList<Tidbit>) : RecyclerView.Adapter<TidbitViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TidbitViewHolder {
        return when (viewType) {
            else -> TidbitViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rvcard_tidbit_simple, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return tidbits.size
    }

    override fun onBindViewHolder(holder: TidbitViewHolder, position: Int) {
        when (getItemViewType(position)) {
            else -> (holder).bind(tidbits[position])
        }
    }

}