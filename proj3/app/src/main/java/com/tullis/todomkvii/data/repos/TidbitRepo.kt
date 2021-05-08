package com.tullis.todomkvii.data.repos

import android.os.Handler
import android.provider.Settings
import com.tullis.todomkvii.TodoApplication
import com.tullis.todomkvii.activities.main.fragments.BacklogFragment
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.data.repos.TidbitCollectionRepo.localSource
import com.tullis.todomkvii.events.AddedTidbitEvent
import com.tullis.todomkvii.events.DataUpdatedEvent
import com.tullis.todomkvii.events.DeletedTidbitEvent
import com.tullis.todomkvii.events.ShiftedTidbitEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

object TidbitRepo {
    lateinit var subscriber :TidbitRepoSubscriber
    val localSource = TodoApplication.db.tidbitDAO()

    fun addAllTidbits (list:ArrayList<Tidbit>) {
        GlobalScope.launch(Dispatchers.Default) {
            list.addAll ( localSource.getAll() )
            EventBus.getDefault().post( DataUpdatedEvent() )
        }
    }

    fun replaceWithTidbitsWithTag (list:ArrayList<Tidbit>, tag:Long) {
        GlobalScope.launch(Dispatchers.Default) {
            val newList = localSource.getAllWithTagID(tag)

            if (list.size!=newList.size || (newList.size>0 && !newList[0].equals(list[0])) ) {
                list.clear()
                list.addAll(localSource.getAllWithTagID(tag))
                EventBus.getDefault().post(DataUpdatedEvent())
            }
        }
    }

    // subscriber
    class TidbitRepoSubscriber () {
        init {
            EventBus.getDefault().register(this)
        }

        @Subscribe
        fun shiftTidbitBetweenCollections (e:ShiftedTidbitEvent) {
            GlobalScope.launch (Dispatchers.Default) {
                //println("asdf tidbitrepo " + e.tidbit.collectionId)
                if (e.tidbit.collectionId.equals(TodoApplication.backlogID)) {
                    localSource.updateCollectionID(e.tidbit.tidbitId, e.newCollectionID)
                } else {
                    localSource.updateCollectionID(e.tidbit.tidbitId, TodoApplication.backlogID)
                }
                EventBus.getDefault().post( DataUpdatedEvent() )
            }
        }

        @Subscribe
        fun addTidbitToLocalSource(e: AddedTidbitEvent) {
            GlobalScope.launch(Dispatchers.Default) {
                localSource.insertAll(e.tidbit)
                EventBus.getDefault().post( DataUpdatedEvent() )
            }
        }

        @Subscribe
        fun deleteTidbitFromLocalSource(e:DeletedTidbitEvent) {
            GlobalScope.launch (Dispatchers.Default) {
                localSource.delete(e.tidbit)
                EventBus.getDefault().post( DataUpdatedEvent() )
            }
        }
    }

}