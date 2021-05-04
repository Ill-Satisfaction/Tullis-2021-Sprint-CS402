package com.example.todomkvi.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todomkvi.R
import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.recyclers.TidbitAdapter
import com.example.todomkvi.repositories.CollectionRepo
import com.example.todomkvi.ui.main.PageViewModel
import com.example.todomkvi.ui.main.PlaceholderFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BacklogFragment : TidbitCollectionFragmentInterface() {
    override val title = "backlog"
}