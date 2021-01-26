package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv:TextView = findViewById(R.id.tv_counter)
        var btn:Button = findViewById(R.id.button)
        var counter = 0

        btn?.setOnClickListener() {
            counter++
            tv.text=counter.toString()
        }

    }
}