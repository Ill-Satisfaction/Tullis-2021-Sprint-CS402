package com.example.todomk2.UI.Activities.Fragments

import android.os.Bundle
import com.example.todomk2.Interfaces.ListFragmentInterface
import com.example.todomk2.Models.LIST_ID
import com.example.todomk2.Models.TidbitListModel
import com.example.todomk2.Models.TidbitModel

class TodayListFragment :ListFragmentInterface() {

    val id :LIST_ID = LIST_ID.TODAY
    val destId :LIST_ID = LIST_ID.BACKLOG

    override fun loadList(tlm: TidbitListModel) {
        tlm.loadTidbits(id)
    }

    override fun getTitle(): String {
        return "today" //TODO:fix hardcoded string
    }

    override fun getListId(): LIST_ID {
        return id
    }

    override fun getDestinationId(): LIST_ID {
        return destId
    }
}