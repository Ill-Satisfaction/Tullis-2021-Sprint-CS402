package com.example.todomkvi.repositories.providers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todomkvi.data.Tidbit

// data access object with Room

@Dao
interface TidbitDataProviderLocalDatabase :TidbitDataProviderInterface {

    @Query ("SELECT * FROM tidbit")
    override fun getAll(): List<Tidbit>

    @Query ("SELECT * FROM tidbit WHERE collectionId = (:tagID)")
    override fun getAllWithTagID(tagID: Int): List<Tidbit>

    @Insert
    override fun insertAll(vararg tidbits:Tidbit)


}