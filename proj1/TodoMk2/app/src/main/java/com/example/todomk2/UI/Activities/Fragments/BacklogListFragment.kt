package com.example.todomk2.UI.Activities.Fragments

import android.os.Bundle
import android.util.Log
import com.example.todomk2.Events.NewTidbitExitEvent
import com.example.todomk2.Interfaces.ListFragmentInterface
import com.example.todomk2.Models.LIST_ID
import com.example.todomk2.Models.TidbitListModel
import com.example.todomk2.Models.TidbitModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class BacklogListFragment :ListFragmentInterface() {

    val id :LIST_ID = LIST_ID.BACKLOG
    val destID :LIST_ID = LIST_ID.TODAY

    // implement interface
    override fun loadList(tlm: TidbitListModel) {
        tlm.loadTidbits(id)
    }

    override fun getTitle(): String {
        return "backlog" //TODO: fix hardcoded string
    }

    override fun getListId(): LIST_ID {
        return id
    }

    override fun getDestinationId(): LIST_ID {
        return destID
    }

    // custom
    @Subscribe
    fun onNewTidbitExit (event : NewTidbitExitEvent) {
        if (event.success) {
            addItem(event.tidbit)
        }
    }
}