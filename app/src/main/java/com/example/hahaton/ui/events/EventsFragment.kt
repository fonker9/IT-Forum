package com.example.hahaton.ui.events

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.data.repository.EventStorage
import com.example.hahaton.databinding.FragmentHomeEventsBinding
import com.example.hahaton.R
import com.example.hahaton.ui.EventAdapter

class EventsFragment : Fragment() {
    private var _binding: FragmentHomeEventsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Данные для примера
        val events = listOf("Event 1", "Event 2", "Event 3", "Event 4", "Event 5")
        val eventsPast = listOf("Past Event 1", "Past Event 2")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerEvents)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = EventAdapter(events)


        val recyclerViewPast: RecyclerView = view.findViewById(R.id.recyclerEventsPast)

        recyclerViewPast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPast.adapter = EventAdapter(eventsPast)
    }
}