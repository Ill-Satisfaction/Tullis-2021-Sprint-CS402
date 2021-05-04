package com.example.todomkvi.repositories

import com.example.todomkvi.data.CollectionWithTidbits
import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.data.TidbitCollection
import com.example.todomkvi.eventbus.AddTidbitEvent
import com.example.todomkvi.repositories.providers.CollectionDataProviderLocalDatabase
import com.example.todomkvi.repositories.providers.TidbitDataProviderLocalDatabase_Impl
import com.example.todomkvi.singletons.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

object CollectionRepo {

    //val subscriber = CollectionRepoSubscriber()
    val localSource = Singleton.db.collectionDAO()
    val loadedCollections = ArrayList<TidbitCollection>()
    val loadedCollectionsWithTidbits = ArrayList<CollectionWithTidbits>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            loadInitialCollections()

            loadedCollections.addAll(localSource.getAll())
            loadedCollectionsWithTidbits.addAll((localSource.getCollectionsWithTidbits()))
            println("asdf" + loadedCollections.toString())


        }
    }

    // funs
    fun loadInitialCollections () {
        val initialColls = arrayOf(
            TidbitCollection(0, "backlog"),
            TidbitCollection(1, "today")
        )

        initialColls.forEach {
            localSource.insertUnlessExisting(it)
        }
    }

    fun getCollectionIdByName(name:String) : Long? {
        return localSource.getIdByTitle(name)
    }

    fun getCollectionWithTidbitsByName(name:String) :CollectionWithTidbits? {
        if (loadedCollectionsWithTidbits.size == 0) {
            loadedCollectionsWithTidbits.addAll((localSource.getCollectionsWithTidbits()))
        }

        loadedCollectionsWithTidbits.forEach {
            println("asdf " + it.collection.title)
            if (it.collection.title == name) {
                return it
            }
        }
        return null
    }

    // eventbus subscriber
   // class CollectionRepoSubscriber () {
        //init {
            //EventBus.getDefault().register(this)
        //}
    //}
}