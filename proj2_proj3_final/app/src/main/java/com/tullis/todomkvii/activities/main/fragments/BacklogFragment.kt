package com.tullis.todomkvii.activities.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tullis.todomkvii.R
import com.tullis.todomkvii.TodoApplication
import com.tullis.todomkvii.data.recyclers.components.adapters.TidbitAdapter
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.repos.TidbitRepo
import com.tullis.todomkvii.events.DataUpdatedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BacklogFragment : Fragment() {

    companion object {
        val list = ArrayList<Tidbit> ()
        val rvAdapter = TidbitAdapter(list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        // get list from id
        refreshList()

        val rv = root.findViewById<RecyclerView>(R.id.rv_fragment_main)

        rv.apply {
            layoutManager = LinearLayoutManager(root.context, RecyclerView.VERTICAL, false)
            adapter = rvAdapter
        }

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun refreshList () {
        TidbitRepo.replaceWithTidbitsWithTag(list, TodoApplication.backlogID)
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    fun refreshList (e:DataUpdatedEvent) {
        refreshList()
        rvAdapter.notifyDataSetChanged()
    }

}