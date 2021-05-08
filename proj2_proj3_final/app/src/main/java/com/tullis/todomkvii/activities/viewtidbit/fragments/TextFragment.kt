package com.tullis.todomkvii.activities.viewtidbit.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tullis.todomkvii.R

class TextFragment : Fragment() {

    companion object {
        var bodyText = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.view_fragment_text, container, false)
        val body = root.findViewById<TextView>(R.id.tv_view_fragment_body)
        body.text = bodyText
        return root
    }

}