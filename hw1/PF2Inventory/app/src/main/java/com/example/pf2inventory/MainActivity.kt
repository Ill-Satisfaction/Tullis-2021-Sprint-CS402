package com.example.pf2inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pf2inventory.Adapters.ItemAdapter
import com.example.pf2inventory.Models.Item

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_items)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val items = ArrayList<Item>()

        // dummy data
        items.add(Item("item one"))
        items.add(Item("item two"))
        items.add(Item("item three"))
        items.add(Item("item four"))

        val ocl:View.OnClickListener = View.OnClickListener {}

        val adapter = ItemAdapter(items)
        rv.adapter = adapter
    }
}