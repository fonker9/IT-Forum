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
import com.google.firebase.Firebase

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
        val adapter = EventsAdapter()
        val adapterPast = EventsAdapter()

        val fs = Firebase.

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerEvents)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = EventsAdapter()


        val recyclerViewPast: RecyclerView = view.findViewById(R.id.recyclerEventsPast)

        recyclerViewPast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPast.adapter = EventAdapter(eventsPast)
    }
}