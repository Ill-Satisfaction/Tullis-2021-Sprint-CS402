package com.tullis.todomkvii.data.providers

import androidx.room.*
import com.tullis.todomkvii.data.models.TidbitCollection
import com.tullis.todomkvii.data.models.TidbitCollectionWithTidbits

@Dao
interface CollectionDataProviderLocalDatabase {

    // collections

    @Query("SELECT * FROM tidbitCollection")
    fun getAll(): List<TidbitCollection>

    @Query("SELECT cId FROM tidbitCollection WHERE title IS (:title)")
    fun getIdByTitle(title:String) :Long

    // collections with tidbits

    @Transaction
    @Query("SELECT * FROM tidbitCollection")
    fun getCollectionsWithTidbits(): List<TidbitCollectionWithTidbits>

    @Transaction
    @Query("SELECT * FROM tidbitCollection WHERE cId IS (:id)")
    fun getCollectionsWithTidbitsById(id:Long): List<TidbitCollectionWithTidbits>

    @Transaction
    @Query("SELECT * FROM tidbitCollection WHERE title IS (:title)")
    fun getCollectionsWithTidbitsByTitle(title:String): List<TidbitCollectionWithTidbits>

}