package com.tullis.todomkvii.data.recyclers.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tullis.todomkvii.R
import com.tullis.todomkvii.data.models.ContentType
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.recyclers.components.viewholders.TidbitViewHolder
import com.tullis.todomkvii.data.recyclers.components.viewholders.TidbitViewHolderBasic

class TidbitAdapter (val tidbits:ArrayList<Tidbit>) :RecyclerView.Adapter<TidbitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TidbitViewHolder {
        return when (viewType) {
            ContentType.BASIC.value -> TidbitViewHolderBasic(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.rvcard_tidbit_basic, parent, false)
            )
            else -> TidbitViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.rvcard_tidbit_basic, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return tidbits[position].ctype.value
    }

    override fun getItemCount(): Int {
        return tidbits.size
    }

    override fun onBindViewHolder(holder: TidbitViewHolder, position: Int) {
        (holder).bind(tidbits[position])
    }
}