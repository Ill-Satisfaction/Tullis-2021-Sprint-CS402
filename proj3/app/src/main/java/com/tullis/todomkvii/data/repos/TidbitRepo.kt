package com.tullis.todomkvii.data.repos

import android.provider.Settings
import com.tullis.todomkvii.TodoApplication
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.repos.TidbitCollectionRepo.localSource
import com.tullis.todomkvii.events.AddedTidbitEvent
import com.tullis.todomkvii.events.DataUpdatedEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

object TidbitRepo {
    lateinit var subscriber :TidbitRepoSubscriber
    val localSource = TodoApplication.db.tidbitDAO()

    fun addAllTidbits (list:ArrayList<Tidbit>) {
        GlobalScope.launch(Dispatchers.Default) {
            list.addAll ( localSource.getAll() )
        }
    }

    fun addTidbitsWithTag (list:ArrayList<Tidbit>, tag:Long) {
        GlobalScope.launch(Dispatchers.Default) {
            list.addAll( localSource.getAllWithTagID(tag) )
        }
    }

    fun deleteTidbit (t:Tidbit) {
        GlobalScope.launch (Dispatchers.Default) {
            localSource.delete(t)
            EventBus.getDefault().post( DataUpdatedEvent() )
        }
    }

    // subscriber
    class TidbitRepoSubscriber () {
        init {
            EventBus.getDefault().register(this)
        }

        @Subscribe
        fun addTidbitToLocalSource(e: AddedTidbitEvent) {
            GlobalScope.launch(Dispatchers.Default) {
                localSource.insertAll(e.tidbit)
            }
        }
    }

}