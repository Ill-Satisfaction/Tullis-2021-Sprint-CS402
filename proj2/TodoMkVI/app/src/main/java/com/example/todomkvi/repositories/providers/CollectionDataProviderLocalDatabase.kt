package com.example.todomkvi.repositories.providers

import androidx.room.*
import com.example.todomkvi.data.CollectionWithTidbits
import com.example.todomkvi.data.TidbitCollection

// data access object with Room

@Dao
interface CollectionDataProviderLocalDatabase {

    // collections

    @Query("SELECT * FROM tidbitCollection")
    fun getAll(): List<TidbitCollection>

    @Query("SELECT cId FROM tidbitCollection WHERE title IS (:title)")
    fun getIdByTitle(title:String) :Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUnlessExisting(collection:TidbitCollection)



    // collections with tidbits

    @Transaction
    @Query("SELECT * FROM tidbitCollection")
    fun getCollectionsWithTidbits(): List<CollectionWithTidbits>

    @Transaction
    @Query("SELECT * FROM tidbitCollection WHERE cId IS (:id)")
    fun getCollectionsWithTidbitsById(id:Long): List<CollectionWithTidbits>

    @Transaction
    @Query("SELECT * FROM tidbitCollection WHERE title IS (:title)")
    fun getCollectionsWithTidbitsByTitle(title:String): List<CollectionWithTidbits>

}