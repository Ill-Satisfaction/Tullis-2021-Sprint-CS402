package com.tullis.todomkvii.data.recyclers.components.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tullis.todomkvii.R
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.events.GoToViewTidbitActivityEvent
import org.greenrobot.eventbus.EventBus

open class TidbitViewHolder ( val v: View) :RecyclerView.ViewHolder (v) {
    open fun bind (t: Tidbit) {

        // apply listeners
        v.setOnClickListener {
            EventBus.getDefault().post(GoToViewTidbitActivityEvent(t))
        }

//        v.setOnLongClickListener {
//            //EventBus.getDefault().post(DeleteTidbitEvent(adapterPosition))
//            return@setOnLongClickListener true
//        }
    }
}

// basic
class TidbitViewHolderBasic (v:View) :
    TidbitViewHolder(v) {
    override fun bind (t:Tidbit) {
        super.bind(t)

        //change fields
        val title = itemView.findViewById<TextView>(R.id.tv_rvcard_basic_title)
        title.text = t.title
    }
}