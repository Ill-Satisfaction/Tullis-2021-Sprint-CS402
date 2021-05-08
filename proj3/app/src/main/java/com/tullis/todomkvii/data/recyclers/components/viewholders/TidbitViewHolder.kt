package com.tullis.todomkvii.data.recyclers.components.viewholders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tullis.todomkvii.R
import com.tullis.todomkvii.TodoApplication
import com.tullis.todomkvii.data.Converters
import com.tullis.todomkvii.data.models.Tidbit
import com.tullis.todomkvii.events.GoToViewTidbitActivityEvent
import com.tullis.todomkvii.events.ShiftedTidbitEvent
import org.greenrobot.eventbus.EventBus

open class TidbitViewHolder ( val v: View) :RecyclerView.ViewHolder (v) {
    open fun bind (t: Tidbit) {

        // apply listeners
        v.setOnClickListener {
            EventBus.getDefault().post(GoToViewTidbitActivityEvent(t))
        }

        v.setOnLongClickListener {
            EventBus.getDefault().post(ShiftedTidbitEvent(t, TodoApplication.currentID))
            return@setOnLongClickListener true
        }
    }
}

// basic or text
class TidbitViewHolderBasic (v:View) :
    TidbitViewHolder(v) {
    override fun bind (t:Tidbit) {
        super.bind(t)

        //change fields
        val title = itemView.findViewById<TextView>(R.id.tv_rvcard_basic_title)
        title.text = t.title
    }
}

// image
class TidbitViewHolderImage (v:View) :
    TidbitViewHolder(v) {
    override fun bind (t:Tidbit) {
        super.bind(t)

        //change fields
        val title = itemView.findViewById<TextView>(R.id.tv_rvcard_image_title)
        title.text = t.title
        val image = itemView.findViewById<ImageView>(R.id.iv_rvcard_image)
        image.setImageBitmap(Converters.stringToBitmap(t.content!!))
    }
}