package com.tullis.todomkvii.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tullis.todomkvii.TodoApplication
import java.io.Serializable

/*
 *  Room Data Entity
 */

// content types
enum class ContentType (val value:Int) {
    BASIC(1),
    TEXT(2),
    IMAGE(3),
    AUDIO(4)
}

@Entity
data class Tidbit (
    @PrimaryKey(autoGenerate=true) val tidbitId :Long,
    @ColumnInfo var collectionId :Long,
    @ColumnInfo(name = "title") val title :String,
    @ColumnInfo(name = "contentType") val ctype : ContentType,
    @ColumnInfo(name = "content") val content :String?
) :Serializable {
    //  CONSTRUCTORS
    constructor(title:String) :this (
        TodoApplication.AUTO_INCREMENT,
        TodoApplication.backlogID,
        title, ContentType.BASIC,
        null
    )

    constructor(title:String, ctype:ContentType, content:String) :this (
        TodoApplication.AUTO_INCREMENT,
        TodoApplication.backlogID,
        title, ctype,
        content
    )
}