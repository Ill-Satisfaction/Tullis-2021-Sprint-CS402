package com.example.todomkvi.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todomkvi.R
import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.recyclers.TidbitAdapter
import com.example.todomkvi.repositories.CollectionRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class TidbitCollectionFragmentInterface : FragmentInterfaceMainActivity() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        // setup recycler
        // get list from id
        val list = ArrayList<Tidbit>()
        GlobalScope.launch {
            CollectionRepo.getCollectionWithTidbitsByName(title)?.tidbits?.let { list.addAll(it) }
        }

        val rv = root.findViewById<RecyclerView>(R.id.rv_fragment)
        val rvAdapter = TidbitAdapter(list)

        rv!!.apply {
            layoutManager = LinearLayoutManager(root.context, RecyclerView.VERTICAL, false)
            adapter = rvAdapter
        }

        return root
    }

}