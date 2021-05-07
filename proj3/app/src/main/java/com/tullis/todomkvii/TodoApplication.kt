package com.tullis.todomkvii

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.models.TidbitCollection
import com.tullis.todomkvii.data.providers.CollectionDataProviderLocalDatabase
import com.tullis.todomkvii.data.providers.TidbitDataProviderLocalDatabase
import com.tullis.todomkvii.data.repos.TidbitRepo


class TodoApplication : Application() {

    companion object {
        val AUTO_INCREMENT :Long = 0;
        var backlogID :Long = 0;

        lateinit var db :AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // create Room database
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todomkvii-database"
        )
            .fallbackToDestructiveMigration()   //clear old database if schema updates
            .build()
    }
}

// room database
@Database(
    entities = [Tidbit::class, TidbitCollection::class],
    version = 2,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase :RoomDatabase () {
    abstract fun tidbitDAO () :TidbitDataProviderLocalDatabase
    abstract fun tidbitCollectionDAO () : CollectionDataProviderLocalDatabase
}