package com.tullis.todomkvii.data.repos

import com.tullis.todomkvii.TodoApplication
import com.tullis.todomkvii.data.models.TidbitCollectionWithTidbits
import com.tullis.todomkvii.events.AddedTidbitEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

object TidbitCollectionRepo {

    val currentRepos = ArrayList<TidbitCollectionWithTidbits>()
    val subscriber = TidbitCollectionRepoSubscriber()
    val localSource = TodoApplication.db.tidbitDAO()

    init {}

    // subscriber
    class TidbitCollectionRepoSubscriber () {
        init {
            EventBus.getDefault().register(this)
        }

        @Subscribe
        fun addTidbitToLocalSource (e:AddedTidbitEvent) {
            // TODO: implement some subscribe functions
        }
    }
}