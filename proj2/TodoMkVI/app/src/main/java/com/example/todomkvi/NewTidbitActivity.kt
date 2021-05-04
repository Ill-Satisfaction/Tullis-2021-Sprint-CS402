package com.example.todomkvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.todomkvi.data.ContentType
import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.eventbus.AddTidbitEvent
import org.greenrobot.eventbus.EventBus

class NewTidbitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tidbit)

        val root = this
        var btnSave = findViewById<Button>(R.id.btn_addtidbit_add)
        val etTitle = findViewById<EditText>(R.id.et_addtidbit_title)
        val etContent = findViewById<EditText>(R.id.et_addtidbit_description)

        btnSave.setOnClickListener {
            EventBus.getDefault().post( AddTidbitEvent(
                Tidbit(etTitle.text.toString(), ContentType.BASIC, etContent.text.toString())
            ))
            finish()
        }
    }
}