package com.example.todomkvi.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class CollectionWithTidbits (
    @Embedded val collection:TidbitCollection,
    @Relation(
        parentColumn = "cId",
        entityColumn = "collectionId"
    )
    val tidbits: List<Tidbit>
)