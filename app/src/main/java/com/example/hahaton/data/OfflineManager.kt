package com.example.hahaton.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.PersistentCacheSettings
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.firestore.persistentCacheSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object OfflineManager {
    private val db = Firebase.firestore

    init {
//        enablePersistence()
    }

    fun enablePersistence() {
        val settings = firestoreSettings {
            setLocalCacheSettings(persistentCacheSettings {  })
        }

        db.firestoreSettings = settings
    }

    suspend fun getData(collection: String, documentId: String): DocumentSnapshot? {
        return withContext(Dispatchers.IO) {
            try {
                db.collection(collection).document(documentId)
                    .get(Source.CACHE)
                    .await()
            } catch (e: Exception) {
                try {
                    db.collection(collection).document(documentId)
                        .get(Source.SERVER)
                        .await()
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}