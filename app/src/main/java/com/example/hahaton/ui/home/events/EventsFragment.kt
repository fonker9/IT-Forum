package com.example.hahaton.ui.home.events

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.data.repository.EventRepository
import com.example.hahaton.databinding.FragmentHomeEventsBinding
import com.example.hahaton.R
import com.example.hahaton.data.model.Event
import com.example.hahaton.ui.EventAdapter
import com.example.hahaton.ui.admin.EventAddActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val adapater: EventsAdapter = EventsAdapter()

        _firebaseListener = Firebase.firestore.collection("events")
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

                    adapater.submitList(events)
                }
            }

        // Данные для примера
        val eventsPast = listOf("Past Event 1", "Past Event 2")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerEvents)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapater


        val recyclerViewPast: RecyclerView = view.findViewById(R.id.recyclerEventsPast)

        recyclerViewPast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPast.adapter = EventAdapter(eventsPast)


        // Кнопка редактирования мероприятий
        val buttonAdmin: Button = binding.buttonAdmin
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser // Проверяем текущего пользователя
        if (currentUser != null && currentUser.uid == "a0W1CVqGozOCp7UbFlqDS7ytLqj1") {
            buttonAdmin.visibility = View.VISIBLE // Пользователь с нужным UID, показываем кнопку
        } else {
            buttonAdmin.visibility = View.GONE // Другой пользователь или пользователь не авторизован, скрываем кнопку
        }

        buttonAdmin.setOnClickListener {
            val intent = Intent(requireContext(), EventAddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _firebaseListener = null
    }

}