package com.tullis.todomkvii.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.tullis.todomkvii.R
import com.tullis.todomkvii.activities.viewtidbit.fragments.ImageFragment
import com.tullis.todomkvii.activities.viewtidbit.fragments.TextFragment
import com.tullis.todomkvii.activities.viewtidbit.fragments.ViewTidbitFragment
import com.tullis.todomkvii.data.models.ContentType
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.events.DeletedTidbitEvent
import org.greenrobot.eventbus.EventBus

class ViewTidbitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tidbit_activity)

        // set title
        val tvTitle = findViewById<TextView>(R.id.tv_viewtidbit_title)
        val tidbit = intent.getSerializableExtra("tidbit") as Tidbit
        tvTitle.text = title

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, when (tidbit.ctype) {
                    ContentType.TEXT -> {
                        TextFragment.bodyText = tidbit.content.toString()
                        TextFragment()
                    }
                    else -> ViewTidbitFragment.newInstance()
                })
                .commitNow()
        }

        // setup button listeners
        val btnBack = findViewById<Button>(R.id.btn_viewtidbit_back)
        btnBack.setOnClickListener {
            finish()
        }

        val btnTrash = findViewById<ImageButton>(R.id.btn_viewtidbit_trash)
        btnTrash.setOnClickListener {
            EventBus.getDefault().post( DeletedTidbitEvent(tidbit) )
            finish()
        }
    }
}