package com.example.hahaton.ui.home.events

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.R
import com.example.hahaton.data.model.Event
import com.example.hahaton.databinding.FragmentHomeEventsBinding
import com.example.hahaton.ui.event.EventActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class EventsAdapter() : ListAdapter<Event, EventsAdapter.ViewHolder>(DiffCallback())  {
    class DiffCallback: DiffUtil.ItemCallback<Event>() {
        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ViewHolder(
        itemView: View,
        private val context: Context
    ): RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.eventTitle)
        private val dateTextView: TextView = itemView.findViewById(R.id.eventDate)
        private val dateTextViewMin: TextView = itemView.findViewById(R.id.eventDateMin)
        private val visitButton: Button = itemView.findViewById(R.id.visitButton)

        fun bind(event: Event) {
            val dateStart = event.getStartDate()
            val dateEnd = event.getEndDate()

            titleTextView.text = event.title
            dateTextView.text = SimpleDateFormat("dd.MM").format(dateStart)
            dateTextViewMin.text = SimpleDateFormat("HH:mm").format(dateStart) + " - " + SimpleDateFormat("HH:mm").format(dateEnd)

            visitButton.setOnClickListener {
                val intent = Intent(itemView.context, EventActivity::class.java)
                intent.putExtra("eventId", event.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}