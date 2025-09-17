package com.example.hahaton.data.remote

import android.util.Log
import com.example.hahaton.data.OfflineManager
import com.example.hahaton.data.model.Event
import com.example.hahaton.data.model.Speaker
import com.example.hahaton.data.model.SubEvent
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat

class FirebaseEventDataSource {
    private val db = Firebase.firestore
    private val eventsCollection = db.collection("events")


    fun saveEvent(event: Event, subevents: List<SubEvent>) {
        eventsCollection.document(event.id).set(event)

        for (subevent in subevents) {
            eventsCollection.document(event.id).collection("subevents").document(subevent.id).set(subevent)
        }
    }

    suspend fun getEvents(): List<Event> {
        val snapshot = eventsCollection.get().await()

        Log.d("test", snapshot.isEmpty.toString())

        return listOf()
    }

    suspend fun getEvent(id: String): Event? {
        return OfflineManager.getData("events", id)?.toObject(Event::class.java)
    }

    fun createTestEntries() {
        val events = listOf(
            object {
                val event = Event("UNIQUE_1", "Мероприятие #1")
                val subevents = listOf(
                    SubEvent(
                        "UNIQUE_1",
                        "Собрание",
                        Speaker("UNIQUE_1", "Александр", "Голунов", "Владимирович"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 18:30")
                    ),
                    SubEvent(
                        "UNIQUE_1",
                        "Собрание",
                        Speaker("UNIQUE_1", "Александр", "Голунов", "Владимирович"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 18:30")
                    ),
                )
            }
        )

        for (entry in events) {
            saveEvent(entry.event, entry.subevents)
        }
    }
}