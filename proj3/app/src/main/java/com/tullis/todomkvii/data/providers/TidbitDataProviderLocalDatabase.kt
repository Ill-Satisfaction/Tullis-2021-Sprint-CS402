package com.tullis.todomkvii.data.providers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tullis.todomkvii.data.models.Tidbit

// Room DAO

@Dao
interface TidbitDataProviderLocalDatabase {
    @Query ("SELECT * FROM tidbit")
    fun getAll(): List<Tidbit>

    @Query ("SELECT * FROM tidbit WHERE collectionId = (:tagID)")
    fun getAllWithTagID(tagID: Long): List<Tidbit>

    @Query("UPDATE tidbit SET collectionId = :cId WHERE tidbitId =:tId")
    fun updateCollectionID (tId:Long, cId:Long)

    @Insert
    fun insertAll(vararg tidbits: Tidbit)

    @Delete
    fun delete(tidbit: Tidbit)

    @Query("DELETE FROM tidbit WHERE tidbitId = :id")
    fun deleteById(id: Long)


}