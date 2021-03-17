package com.example.todomk2.Models

import android.graphics.Bitmap
import android.util.Log
import java.sql.Types

enum class TIDBIT_TYPE  {
    SHORTTEXT,
    IMAGE;

    companion object {
        fun getInt (type :TIDBIT_TYPE) :Int {
            return when (type) {
                TIDBIT_TYPE.SHORTTEXT -> 0
                TIDBIT_TYPE.IMAGE -> 1
                else -> -1
            }
        }
    }
}

data class TidbitModel  (   var title :String?,
                            var image :Bitmap?
                        ) {
    constructor(title :String) : this(title, null)

    fun getTidbitType () :TIDBIT_TYPE {
        if (image == null) return TIDBIT_TYPE.SHORTTEXT
        else {
            Log.d("tag", "returning tidbittype")
            return TIDBIT_TYPE.IMAGE
        }
    }
}