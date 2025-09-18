package com.example.hahaton.ui.event.speakers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.data.model.Speaker
import com.example.hahaton.databinding.ItemEventSpeakerBinding

class EventSpeakerAdapter() : ListAdapter<Speaker, EventSpeakerAdapter.ViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<Speaker>() {
        override fun areContentsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
            return oldItem.id == newItem.id
        }
    }
    inner class ViewHolder(
        private val binding: ItemEventSpeakerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Speaker) {
            binding.eventSpeakerName.text = item.getFormattedName()
            binding.eventSpeakerDescription.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val binding = ItemEventSpeakerBinding.inflate(infalter, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}