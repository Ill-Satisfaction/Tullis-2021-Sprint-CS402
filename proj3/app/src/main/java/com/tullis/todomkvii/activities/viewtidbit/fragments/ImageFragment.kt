package com.tullis.todomkvii.activities.viewtidbit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tullis.todomkvii.R
import com.tullis.todomkvii.data.Converters

class ImageFragment : Fragment() {

    companion object {
        var imagetext = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.view_fragment_image, container, false)
        val body = root.findViewById<ImageView>(R.id.iv_fragment_view_image)
        body.setImageBitmap(Converters.stringToBitmap(imagetext))

        return root
    }

}