package com.example.todomk2.Models

import android.util.Log
import com.example.todomk2.Adapters.JSONAdapter
import com.example.todomk2.Events.JSONRecievedEvent
import com.example.todomk2.Events.ListLoadedEvent
import com.example.todomk2.Events.TidbitMovedEvent
import com.parse.*
import org.greenrobot.eventbus.EventBus
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

enum class LIST_ID {
    BACKLOG,
    TODAY
}

class TidbitListModel  () {

    var tidbits :ArrayList<TidbitModel> = ArrayList<TidbitModel>()
    var listID :LIST_ID? = null

    fun loadTidbits (id :LIST_ID) {
        listID = id
        JSONAdapter().getTidbitsJSON(id)
    }

    fun tidbitsListRecieved (ja :JSONArray, id :LIST_ID) {
        if (listID != id) return

        Log.d("tag", "got here")

        for (i in 0 until ja.length()) {
            val item = ja.getJSONObject((i))
            if (item.getString("ListID").equals(listID.toString())) {
                tidbits.add(TidbitModel(item.getString("Title")))
            }
        }

        EventBus.getDefault().post(ListLoadedEvent(id))
    }

    fun getList () :ArrayList<TidbitModel> {
        return tidbits
    }

    fun setList (newTidbits :ArrayList<TidbitModel>) {
        tidbits = newTidbits
    }
}