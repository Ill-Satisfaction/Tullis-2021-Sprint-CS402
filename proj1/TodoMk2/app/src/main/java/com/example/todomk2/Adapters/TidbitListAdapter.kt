package com.example.todomk2.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todomk2.Events.DeleteTidbitEvent
import com.example.todomk2.Events.MoveTidbitEvent
import com.example.todomk2.Models.TIDBIT_TYPE
import com.example.todomk2.Models.TIDBIT_TYPE.Companion.getInt
import com.example.todomk2.Models.TidbitModel
import com.example.todomk2.R
import org.greenrobot.eventbus.EventBus

class TidbitListAdapter (   val tidbits :ArrayList<TidbitModel>)
                        :RecyclerView.Adapter<TidbitListAdapter.ViewHolder>() {

    abstract class ViewHolder (itemView : View) :RecyclerView.ViewHolder(itemView) {

        open fun bind (tb :TidbitModel) {
            itemView.setOnClickListener {
                EventBus.getDefault().post(MoveTidbitEvent(adapterPosition))
            }

            itemView.setOnLongClickListener {
                EventBus.getDefault().post(DeleteTidbitEvent(adapterPosition))
                return@setOnLongClickListener true
            }
        }
    }
    class ShorttextViewHolder (itemView: View) :ViewHolder(itemView) {
        override fun bind (tb :TidbitModel) {
            super.bind(tb)
            val title = itemView.findViewById<TextView>(R.id.tv_tidbit_card)
            title.setText(tb.title)
        }
    }

    class ImageViewHolder (itemView: View) :ViewHolder (itemView) {
        override fun bind (tb :TidbitModel) {
            super.bind(tb)

            val title = itemView.findViewById<TextView>(R.id.tv_tidbit_card)
            val image = itemView.findViewById<ImageView>(R.id.iv_tidbit_card_image)

            title.setText(tb.title)
            image.setImageBitmap(tb.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder {
       return when (viewType) {
            getInt( TIDBIT_TYPE.SHORTTEXT) -> ShorttextViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.tidbit_card_simpletext, parent, false)
            )
            getInt( TIDBIT_TYPE.IMAGE) -> ImageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.tidbit_card_image, parent, false)
            )

            else -> ShorttextViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.tidbit_card_simpletext, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getInt( tidbits[position].getTidbitType())
    }

    override fun getItemCount(): Int {
        return tidbits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            getInt( TIDBIT_TYPE.SHORTTEXT) -> {
                Log.d("tag", "making text card")
                (holder as ShorttextViewHolder).bind(tidbits[position])
            }
            getInt( TIDBIT_TYPE.IMAGE) -> {
                Log.d("tag", "making image card")
                (holder as ImageViewHolder).bind(tidbits[position])
            }
        }
        //holder.bind(tidbits[position])
    }
}