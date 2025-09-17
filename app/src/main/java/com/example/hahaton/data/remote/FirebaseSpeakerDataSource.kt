package com.example.hahaton.data.remote

import com.example.hahaton.data.model.Speaker
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirebaseSpeakerDataSource {
    private val db = Firebase.firestore;
    private val speakersCollection = db.collection("speakers")

    fun saveSpeaker(speaker: Speaker) {
        speakersCollection.document(speaker.id).set(speaker)
    }

    fun createTestEntries() {
        val speakers = listOf<Speaker>(
            Speaker("UNIQUE_1", "Александр", "Голунов", "Владимирович"),
            Speaker("UNIQUE_2", "Юрий", "Бахмутский", "Андреевич"),
            Speaker("UNIQUE_3", "Таисья", "Макарова", "Васильевна")
        )

        for (speaker in speakers) {
            saveSpeaker(speaker)
        }
    }
}