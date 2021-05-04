package com.example.todomkvi.repositories

import com.example.todomkvi.data.Tidbit
import com.example.todomkvi.eventbus.AddTidbitEvent
import com.example.todomkvi.repositories.providers.TidbitDataProviderInterface
import com.example.todomkvi.repositories.providers.TidbitDataProviderLocalDatabase
import com.example.todomkvi.repositories.providers.TidbitDataProviderLocalDatabase_Impl
import com.example.todomkvi.singletons.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

object TidbitRepo {

    val subscriber = TidbitRepoSubscriber()
    val localSource = Singleton.db.tidbitDAO()
    val loadedTidbits = ArrayList<Tidbit>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            loadedTidbits.addAll(localSource.getAll())
            //println("${Thread.currentThread()} has run.")
            //println("asdf" + loadedTidbits.toString())
        }
    }

    class TidbitRepoSubscriber () {
        init {
            EventBus.getDefault().register(this)
        }

        @Subscribe
        fun addTidbitToLocalSource(e: AddTidbitEvent) {
            GlobalScope.launch(Dispatchers.Default) {
                var tidbit = e.tidbit
                val cId = CollectionRepo.getCollectionIdByName("backlog")

                //println("asdf " + cId)

                if (cId != null) tidbit.collectionId = cId

                localSource.insertAll(e.tidbit)
            }
        }

    }




}