package com.example.todomk2.Adapters

import android.util.Log
import com.example.todomk2.Events.JSONRecievedEvent
import com.example.todomk2.Models.LIST_ID
import com.example.todomk2.Models.TidbitModel
import org.greenrobot.eventbus.EventBus
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class JSONAdapter {

    var listID :LIST_ID? = null

    val url = URL("https://parseapi.back4app.com/classes/Tidbits")
    val AppID = "xiGu25znChmImRDV3n8bv0PM2AGutpotZPQIv6jy"
    val APIKey = "jIrHaCO5ZIC3XjAyMIYhmhDsDsEZAuPaHgypQAi3"

    fun getTidbitsJSON (listId: LIST_ID) {
        var urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.setRequestProperty("X-Parse-Application-Id", AppID)
        urlConnection.setRequestProperty("X-Parse-REST-API-Key", APIKey)

        var results :JSONArray? = null

        Thread(Runnable {
            try {
                val data = JSONObject(urlConnection.inputStream.bufferedReader().use { it.readText() })
                results = data.getJSONArray("results")
                EventBus.getDefault().post(JSONRecievedEvent(true, listId, results))
            } catch (e: Exception) {
                Log.e("tag", e.toString())
                EventBus.getDefault().post(JSONRecievedEvent(false, listId,null))
            } finally {
                urlConnection.disconnect()
            }
        }).start()
    }

    fun postTidbitJSON (listId: LIST_ID, tidbit :TidbitModel) {
    }

}