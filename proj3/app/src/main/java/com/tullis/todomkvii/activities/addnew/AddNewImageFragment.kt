package com.tullis.todomkvii.activities.addnew

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.tullis.todomkvii.BuildConfig
import com.tullis.todomkvii.R
import java.io.File

class AddNewImageFragment : Fragment() {
    companion object {
        val RC_CAPTURE_IMAGE_ACTIVITY = 100
        var root :Context? = null

        var pFileName = "Photo.jpg"
        var pFile :File? = null
        var ivTidbitImage : ImageView? = null
        var bitmap : Bitmap? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root1 = inflater.inflate(R.layout.fragment_addnew_image, container, false)

        // image button
        root = context
        ivTidbitImage = root1.findViewById(R.id.iv_addnew_image_photo)
        val btnTakePicture = root1.findViewById<ImageButton>(R.id.btn_addnew_image_takepicture)

        btnTakePicture.setOnClickListener {
            launchCamera()
        }

        return root1
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

    fun getPhotoFile (name :String) : File {
        val directoryStorage = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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