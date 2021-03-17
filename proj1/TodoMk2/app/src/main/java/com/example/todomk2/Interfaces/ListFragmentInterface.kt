package com.example.todomk2.Interfaces

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todomk2.Adapters.TidbitListAdapter
import com.example.todomk2.Events.*
import com.example.todomk2.Models.LIST_ID
import com.example.todomk2.Models.TidbitListModel
import com.example.todomk2.Models.TidbitModel
import com.example.todomk2.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.FieldPosition

abstract class ListFragmentInterface :Fragment() {

    val tlm = TidbitListModel()
    var list = ArrayList<TidbitModel>()
    var rvAdapter = TidbitListAdapter(list)

    //  lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        val rv = root.findViewById<RecyclerView>(R.id.rv_list_fragment)

        loadList(tlm)
        list = tlm.getList()
        rvAdapter = TidbitListAdapter(list)

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

    //  custom
    fun addItem (tidbit :TidbitModel) {
        list.add(tidbit)
        rvAdapter?.notifyItemInserted(list.size-1)
    }

    fun removeItem (position :Int) :TidbitModel{
        val item :TidbitModel = list.removeAt(position)
        rvAdapter?.notifyItemRemoved(position)
        return item
    }

    // subscription functions
    @Subscribe
    fun passJSON (event :JSONRecievedEvent) {
        if (event.success && event.id==getListId()) {
            tlm.tidbitsListRecieved(event.results, getListId())
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun reloadList (event :ListLoadedEvent) {
        rvAdapter.notifyDataSetChanged()
    }

    @Subscribe
    fun moveItem (event :MoveTidbitEvent) {
        if (!view?.getGlobalVisibleRect(Rect())!!) return   //  prevents double calls
        val item = removeItem(event.position)

        EventBus.getDefault().post(TidbitMovedEvent(getDestinationId(), item))
    }

    @Subscribe
    fun recieveItem (event :TidbitMovedEvent) {
        if (event.destination != getListId()) return
        addItem(event.item)
    }

    @Subscribe
    fun deleteItem (event: DeleteTidbitEvent) {
        if (!view?.getGlobalVisibleRect(Rect())!!) return   //  prevents double calls
        removeItem(event.position)
    }

    // abstract methods
    abstract fun loadList (tlm :TidbitListModel)
    abstract fun getTitle () :String
    abstract fun getListId () :LIST_ID
    abstract fun getDestinationId () :LIST_ID
}