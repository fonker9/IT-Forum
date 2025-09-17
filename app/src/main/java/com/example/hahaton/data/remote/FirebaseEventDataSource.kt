package com.example.hahaton.data.remote

import android.util.Log
import com.example.hahaton.data.OfflineManager
import com.example.hahaton.data.model.Event
import com.example.hahaton.data.model.Speaker
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.PersistentCacheSettings
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.local.Persistence
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.logging.Logger

class FirebaseEventDataSource {
    private val db = Firebase.firestore;
    private val eventsCollection = db.collection("events")


    fun saveEvent(event: Event) {
        eventsCollection.document(event.id).set(event)
    }

    suspend fun getEvent(id: String): Event? {
        return OfflineManager.getData("events", id)?.toObject(Event::class.java)
    }

    fun createTestEntries() {
        val events = listOf(
            Event("UNIQUE_1", "Мероприятие крутое", "Очень крутое, приходите все", SimpleDateFormat("dd.MM.yyyy").parse("17.09.2025"), null),
            Event("UNIQUE_1", "Туса", "Собрание на даче Голунова", SimpleDateFormat("dd.MM.yyyy").parse("15.06.2026"),
                    listOf(
                        Speaker("UNIQUE_1", "Александр", "Голунов", "Владимирович"),
                    )
                ),
        )

        for (event in events) {
            saveEvent(event)
        }
    }
}