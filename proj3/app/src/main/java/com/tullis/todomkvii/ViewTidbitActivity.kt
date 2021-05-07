package com.tullis.todomkvii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.tullis.todomkvii.activities.main.fragments.BacklogFragment
import com.tullis.todomkvii.activities.viewtidbit.ViewTidbitFragment
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.repos.TidbitRepo
import com.tullis.todomkvii.events.AddedTidbitEvent
import org.greenrobot.eventbus.EventBus

class ViewTidbitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tidbit_activity)

        // set title
        val tvTitle = findViewById<TextView>(R.id.tv_viewtidbit_title)
        val tidbit = intent.getSerializableExtra("tidbit") as Tidbit
        val title = tidbit.title
        tvTitle.text = title

        // setup content fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewTidbitFragment.newInstance())
                .commitNow()
        }

        // setup button listeners
        val btnBack = findViewById<Button>(R.id.btn_viewtidbit_back)
        btnBack.setOnClickListener { finish() }

        val btnTrash = findViewById<ImageButton>(R.id.btn_viewtidbit_trash)
        btnTrash.setOnClickListener {
            TidbitRepo.deleteTidbit(tidbit)
            finish()
        }
    }
}