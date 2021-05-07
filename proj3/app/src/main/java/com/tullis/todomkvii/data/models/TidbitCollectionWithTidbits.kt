package com.tullis.todomkvii.data.models

import androidx.room.Embedded
import androidx.room.Relation

// Room Relation

data class TidbitCollectionWithTidbits (
    @Embedded val collection:TidbitCollection,
    @Relation(
        parentColumn = "cId",
        entityColumn = "collectionId"
    )
    val tidbits: List<Tidbit>
)