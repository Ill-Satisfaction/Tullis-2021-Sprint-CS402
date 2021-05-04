package com.example.todomkvi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

// data entity, uses Room functionality

@Entity
data class TidbitCollection (
    @PrimaryKey val cId :Long,
    @ColumnInfo (name = "title") val title :String
) {
    @Ignore
    val tidbits = ArrayList<Tidbit>()
}