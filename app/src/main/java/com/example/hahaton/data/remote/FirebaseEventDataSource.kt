package com.example.hahaton.data.remote

import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot
import com.example.hahaton.data.OfflineManager
import com.example.hahaton.data.model.Event
import com.example.hahaton.data.model.Speaker
import com.example.hahaton.data.model.SubEvent
import com.example.hahaton.data.model.SubEventType
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat

class FirebaseEventDataSource {
    private val db = Firebase.firestore
    private val eventsCollection = db.collection("events")

    private suspend fun buildEventsList(snapshot: QuerySnapshot): List<Event> {
        val result: MutableList<Event> = ArrayList()

        snapshot.documents.forEach { doc ->
            val snapshotSubevents = doc.reference.collection("subevents").get().await()

            val event = doc.toObject(Event::class.java)
            if (event != null) {
                snapshotSubevents.documents.forEach { docSubevent ->
                    val subevent = docSubevent.toObject(SubEvent::class.java)
                    if (subevent != null) {
                        event.subevents.add(subevent)
                    }
                }

                result.add(event)
            }
        }

        return result
    }

    fun saveEvent(event: Event, subevents: List<SubEvent>) {
        eventsCollection.document(event.id).set(event)

        for (subevent in subevents) {
            eventsCollection.document(event.id).collection("subevents").document(subevent.id).set(subevent)
        }
    }

    suspend fun getEvents(): List<Event> {
        val snapshot = eventsCollection.get().await()

        return buildEventsList(snapshot)
    }

    fun getEventsRealTime(): Flow<List<Event>> = callbackFlow {
        val subscription = eventsCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                close(e)
                return@addSnapshotListener
            }

            async {
                if (snapshot != null) {
                    val events = buildEventsList(snapshot)
                    trySend(events)
                }
            }
        }

        awaitClose { subscription.remove() }
    }

    suspend fun getEvent(id: String): Event? {
        val snapshot = eventsCollection.document(id).get().await()

        val event = snapshot.toObject(Event::class.java)
        if (event != null) {
            val snapshotSubevents = snapshot.reference.collection("subevents").get().await()

            snapshotSubevents.documents.forEach { docSubevent ->
                val subevent = docSubevent.toObject(SubEvent::class.java)
                if (subevent != null) {
                    event.subevents.add(subevent)
                }
            }
        }

        return event
    }

    fun createTestEntries() {
        val events = listOf(
            object {
                val event = Event("UNIQUE_1", "Мероприятие #2", "Конгресс-холл, ул. 75 лет Октября 25 к2")
                val subevents = listOf(
                    SubEvent(
                        "UNIQUE_1",
                        "Название Минимероприятия",
                        Speaker("UNIQUE_1", "Александр", "Голунов", "Владимирович", description = "Крутой товарищ доцент"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 18:30"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 19:30"),
                        "Выставочное пространство, 2 этаж",
                        SubEventType.exhibition,
                    ),
                    SubEvent(
                        "UNIQUE_2",
                        "Название Минимероприятия2",
                        Speaker("UNIQUE_2", "Александр", "Мушкет", "Викторович", description = "Крутой товарищ студент"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 19:40"),
                        SimpleDateFormat("dd.MM.yyyy hh:mm").parse("17.09.2025 20:00"),
                        "Выставочное пространство, 3 этаж",
                        SubEventType.meeting,
                    ),
                )
            }
        )

        for (entry in events) {
            saveEvent(entry.event, entry.subevents)
        }
    }
}