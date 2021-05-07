package com.tullis.todomkvii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.tullis.todomkvii.activities.main.fragments.BacklogFragment
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.events.AddedTidbitEvent
import org.greenrobot.eventbus.EventBus

class AddNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val root = this
        var btnSave = findViewById<Button>(R.id.btn_addnew_save)
        val etTitle = findViewById<EditText>(R.id.et_addnew_header)
        val etContent = findViewById<EditText>(R.id.et_addnew_body)

        btnSave.setOnClickListener {
            val t = Tidbit(etTitle.text.toString())

            EventBus.getDefault().post( AddedTidbitEvent(t) )
            BacklogFragment.list.add(t)
            BacklogFragment.rvAdapter.notifyDataSetChanged ()

            finish()
        }
    }
}