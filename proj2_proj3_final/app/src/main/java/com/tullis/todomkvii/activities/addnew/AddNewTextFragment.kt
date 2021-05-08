package com.tullis.todomkvii.activities.addnew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tullis.todomkvii.R
import com.tullis.todomkvii.activities.viewtidbit.fragments.TextFragment

class AddNewTextFragment : Fragment() {
    companion object {
        lateinit var editText :EditText
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_addnew_text, container, false)
        editText = root.findViewById<EditText>(R.id.et_addnew_text_body)
        return root
    }
}