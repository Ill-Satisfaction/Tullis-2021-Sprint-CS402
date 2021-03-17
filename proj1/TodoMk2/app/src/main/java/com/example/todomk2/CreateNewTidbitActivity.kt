package com.example.todomk2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.example.todomk2.Events.NewTidbitExitEvent
import com.example.todomk2.Models.TidbitModel
import com.parse.ParseObject
import kotlinx.android.synthetic.main.activity_create_new_tidbit.view.*
import org.greenrobot.eventbus.EventBus
import java.io.File

class CreateNewTidbitActivity :Activity() {

    val TAG = "CreateNewTidbitActivity"
    val RC_CAPTURE_IMAGE_ACTIVITY = 100
    var root :Context? = null

    var pFileName = "Photo.jpg"
    var pFile :File? = null
    var ivTidbitImage :ImageView? = null
    var bitmap :Bitmap? = null

    // lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_tidbit)
        root = this

        ivTidbitImage = findViewById<ImageView>(R.id.iv_tidbit_image)
        val btnAddImage = findViewById<Button>(R.id.btn_add_image)
        val btnSave = findViewById<Button>(R.id.btn_newtidbit_save)
        val btnCancel = findViewById<Button>(R.id.btn_newtidbit_cancel)

        btnSave.setOnClickListener {
            val title = findViewById<EditText>(R.id.et_newtidbit_title)
            val tb :TidbitModel = TidbitModel(title.text.toString())
            if (bitmap!=null) tb.image = bitmap

            sendExitEvent(true, tb)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }

        btnAddImage.setOnClickListener {
            launchCamera()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sendExitEvent(false, TidbitModel("ERROR: BAD TIDBIT"))
    }

    // custom
    fun sendExitEvent (success: Boolean, tidbit: TidbitModel) {
        EventBus.getDefault().post(NewTidbitExitEvent(success, tidbit))
    }

    // camera stuff, based on my prior work and a tutorial found at
    // https://medium.com/developer-student-clubs/android-kotlin-camera-using-gallery-ff8591c26c3e
    fun launchCamera () {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        pFile = getPhotoFile(pFileName)
        val providerFile = FileProvider.getUriForFile ( root!!,
            BuildConfig.APPLICATION_ID+".provider", pFile!!)
        i.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        startActivityForResult(i, RC_CAPTURE_IMAGE_ACTIVITY)
    }

    fun getPhotoFile (name :String) :File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(name, ".jpg", directoryStorage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_CAPTURE_IMAGE_ACTIVITY && resultCode == Activity.RESULT_OK) {
            val takenPhoto = BitmapFactory.decodeFile(pFile!!.absolutePath)
            ivTidbitImage!!.setImageBitmap(takenPhoto)
            bitmap = takenPhoto
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}