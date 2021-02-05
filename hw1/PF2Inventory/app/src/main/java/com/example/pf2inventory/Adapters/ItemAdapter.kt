package com.example.pf2inventory.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pf2inventory.DisplayItemInfoActivity
import com.example.pf2inventory.Models.Item
import com.example.pf2inventory.R

class ItemAdapter (val itemList: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:Item = itemList[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_item_name)
        fun bind (item:Item) {
            tv_name.text = (item.itemName)
            itemView.setOnClickListener() {
                val intent = Intent(itemView.context,DisplayItemInfoActivity::class.java)
                intent.putExtra("ITEM_INFO", item)
                itemView.context.startActivity(intent)
            }
        }

    }
}
