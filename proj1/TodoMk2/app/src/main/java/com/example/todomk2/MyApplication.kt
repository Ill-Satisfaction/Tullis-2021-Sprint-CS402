package com.example.todomk2

import android.app.Application
import com.example.todomk2.Models.TidbitModel
import com.parse.Parse
import com.parse.ParseObject

class MyApplication :Application () {

    val appID = "xiGu25znChmImRDV3n8bv0PM2AGutpotZPQIv6jy"
    val cKey = "HFMjHb1rkF73Pa8svQhBxffDQv3ptUrI8NO39E1t"
    val server = "https://parseapi.back4app.com"

    override fun onCreate() {
        super.onCreate()

        //ParseObject.registerSubclass(TidbitModel::class.java)

        Parse.initialize    (   Parse.Configuration.Builder(this)
                                .applicationId(appID)
                                .clientKey(cKey)
                                .server(server)
                                .build ()
                            )
    }

}