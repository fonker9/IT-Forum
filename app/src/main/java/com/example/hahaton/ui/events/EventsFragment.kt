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

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerEvents)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter: EventAdapter = EventAdapter(events)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}