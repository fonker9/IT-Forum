package com.example.hahaton.ui.events

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
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
import com.example.hahaton.data.model.Event
import com.example.hahaton.ui.EventAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
    val fs: FirebaseFirestore = Firebase.firestore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val eventsListener = fs.collection("events")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("EventsFragment", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val events = mutableListOf<Event>()
                    val eventsPast = mutableListOf<String>()

                    for (doc in snapshot.documents) {
                        val event = doc.toObject(Event::class.java) ?: continue
                        events.add(event)
                    }
                }
            }

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


