package com.example.todomkvi.data

import androidx.room.*

// data entity, uses Room functionality

// content types
enum class ContentType {
    BASIC, IMAGE
}

@Entity
data class Tidbit (
    @PrimaryKey(autoGenerate=true) val tidbitId :Long,
    @ColumnInfo var collectionId :Long,
    @ColumnInfo (name = "title") val title :String,
    @ColumnInfo (name = "contentType") val ctype :ContentType,
    @ColumnInfo (name = "content") val content :String
) {
    // add new to backlog
    constructor(title:String, ctype:ContentType, content:String) :this(0,0, title, ctype, content) //todo: set collectionID to match backlog
}