package com.tullis.todomkvii.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

// Room Data Entity

@Entity
data class TidbitCollection (
    @PrimaryKey val cId :Long,
    @ColumnInfo(name = "title") val title :String
) {}