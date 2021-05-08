package com.tullis.todomkvii.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Base64
import android.util.Log
import android.util.Log.e
import android.view.View
import android.widget.*
import com.tullis.todomkvii.R
import com.tullis.todomkvii.activities.addnew.AddNewImageFragment
import com.tullis.todomkvii.activities.addnew.AddNewTextFragment
import com.tullis.todomkvii.activities.main.fragments.BacklogFragment
import com.tullis.todomkvii.activities.viewtidbit.fragments.TextFragment
import com.tullis.todomkvii.activities.viewtidbit.fragments.ViewTidbitFragment
import com.tullis.todomkvii.data.Converters
import com.tullis.todomkvii.data.models.ContentType
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.events.AddedTidbitEvent
import org.greenrobot.eventbus.EventBus
import java.io.ByteArrayOutputStream

enum class EntryType (pos:Int) {
    TEXT (0),
    IMAGE (1),
    AUDIO (2),
    COLLECTION (3)
}

class AddNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        println("asdf asdf")

        // setup vars
        val root = this
        var btnSave = findViewById<Button>(R.id.btn_addnew_save)
        var btnCancel = findViewById<Button>(R.id.btn_addnew_cancel)
        val etTitle = findViewById<EditText>(R.id.et_addnew_header)
        var currentEntryType = EntryType.TEXT

        // setup initial view
        switchView(currentEntryType)

        // setup spinner
        val entryTypes = resources.getStringArray(R.array.new_entry_types)
        val spinner = findViewById<Spinner>(R.id.spin_entry_type)
        spinner.apply {
            adapter = ArrayAdapter(root, android.R.layout.simple_spinner_item, entryTypes)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}  // do nothing

            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                currentEntryType = EntryType.values()[position]
                switchView(currentEntryType)
            }
        }

        // setup cancel button functionality
        /*btnCancel.setOnClickListener {
            finish()
        }*/

        // setup save button functionality
        btnSave.setOnClickListener {
            if (currentEntryType == EntryType.COLLECTION) {
                // TODO: implement collections
            } else {
                val t = when (currentEntryType) {
                    EntryType.TEXT -> Tidbit(
                        etTitle.text.toString(),
                        ContentType.TEXT,
                        AddNewTextFragment.editText.text.toString()
                    )
                    EntryType.IMAGE -> Tidbit(
                        etTitle.text.toString(),
                        ContentType.IMAGE,
                        //BitMapToString(AddNewImageFragment.bitmap!!)
                        Converters.bitmapToString(AddNewImageFragment.bitmap!!)
                    )
                    //EntryType.AUDIO ->
                    else -> Tidbit(etTitle.text.toString())
                }
                // cull text submissions with no title, else send
                if ( t.ctype==ContentType.TEXT && t.title=="") {
                } else {
                    EventBus.getDefault().post(AddedTidbitEvent(t))
                }
            }
            finish()
        }
    }

    fun switchView (type:EntryType) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    when (type) {
                        EntryType.TEXT -> AddNewTextFragment()
                        EntryType.IMAGE -> AddNewImageFragment()
                        else -> ViewTidbitFragment.newInstance()
                })
                .commitNow()
    }
}