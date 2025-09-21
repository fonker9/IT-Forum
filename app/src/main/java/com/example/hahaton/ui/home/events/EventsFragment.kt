package com.example.hahaton.ui.home.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.R
import com.example.hahaton.data.model.Event
import com.example.hahaton.data.repository.EventRepository
import com.example.hahaton.databinding.FragmentHomeEventsBinding
import com.example.hahaton.ui.EventAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import kotlinx.coroutines.launch

class EventsFragment : Fragment() {
    val fs: FirebaseFirestore = Firebase.firestore
    private var _binding: FragmentHomeEventsBinding? = null
    private val binding get() = _binding!!

    private var _firebaseListener: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventsAdapter()

        lifecycleScope.launch {
            EventRepository.getEventsRealTime().collect { events ->
                for (event in events) {
                    Log.d("test", event.subevents.size.toString())
                }

                adapter.submitList(events)
            }
        }


        // Данные для примера
        val eventsPast = listOf("Past Event 1", "Past Event 2")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerEvents)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter


        val recyclerViewPast: RecyclerView = view.findViewById(R.id.recyclerEventsPast)

        recyclerViewPast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPast.adapter = EventAdapter(eventsPast)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _firebaseListener = null
    }

}