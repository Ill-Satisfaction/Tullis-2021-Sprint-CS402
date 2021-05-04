package com.example.todomkvi.singletons

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todomkvi.data.CollectionWithTidbits
import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.data.TidbitCollection
import com.example.todomkvi.repositories.providers.CollectionDataProviderLocalDatabase
import com.example.todomkvi.repositories.providers.TidbitDataProviderLocalDatabase

object Singleton {
    lateinit var db :AppDatabase

    fun buildDatabase (c:Context) {
        db = Room.databaseBuilder(
            c,
            AppDatabase::class.java, "todomkvi-database"
        )
            .fallbackToDestructiveMigration()     //uncomment if database schema updates and you need to clear the old one
        .build()

    }
}

@Database (
    entities = [Tidbit::class, TidbitCollection::class],
    version = 7,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase :RoomDatabase () {
    abstract fun tidbitDAO () :TidbitDataProviderLocalDatabase
    abstract fun collectionDAO () : CollectionDataProviderLocalDatabase
}