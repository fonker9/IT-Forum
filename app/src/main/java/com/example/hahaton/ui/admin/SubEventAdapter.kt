package com.example.hahaton.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.data.model.SubEvent
import com.example.hahaton.databinding.ItemSubEventBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SubEventAdapter(
    private val items: List<SubEvent>
) : RecyclerView.Adapter<SubEventAdapter.SubEventViewHolder>() {

    inner class SubEventViewHolder(private val binding: ItemSubEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        fun bind(item: SubEvent) {
            binding.eventTitle.text = item.title
            binding.eventDate.text = "${item.place} • ${dateFormat.format(item.date)}"
            binding.eventDateMin.text = item.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubEventViewHolder {
        val binding = ItemSubEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubEventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}