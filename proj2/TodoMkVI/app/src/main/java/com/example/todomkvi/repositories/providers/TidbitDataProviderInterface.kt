package com.example.todomkvi.repositories.providers

import com.example.todomkvi.data.Tidbit

interface TidbitDataProviderInterface {
    fun getAll() :List<Tidbit>
    fun getAllWithTagID(tagID :Int) :List<Tidbit>

    fun insertAll(vararg tidbits:Tidbit)
}