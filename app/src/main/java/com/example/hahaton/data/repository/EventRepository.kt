package com.example.hahaton.data.repository

import com.example.hahaton.data.model.Event
import com.example.hahaton.data.remote.FirebaseEventDataSource
import kotlinx.coroutines.flow.Flow

object EventRepository {
    private val dataSource = FirebaseEventDataSource()

    suspend fun getEvents(): List<Event> {
        return dataSource.getEvents()
    }

    suspend fun getEvent(id: String): Event? {
        return dataSource.getEvent(id)
    }

    fun getEventsRealTime(): Flow<List<Event>> {
        return dataSource.getEventsRealTime()
    }

    fun createTestEntries() {
        dataSource.createTestEntries()
    }
}