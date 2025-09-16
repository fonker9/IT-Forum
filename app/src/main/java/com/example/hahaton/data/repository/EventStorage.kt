package com.example.hahaton.data.repository

import com.example.hahaton.data.model.Event

object EventStorage {
    private val events = mutableListOf<Event>()

    init {
        val currentTime = System.currentTimeMillis()
        val dayInMillis = 60 * 60 * 24 * 1000L

        events.addAll(listOf(
            Event(
                title = "Важное событие",
                description = "Очень важное, не опаздывать",
                date = currentTime + dayInMillis * 1
            ),
            Event(
                title = "Собрание важных - бумажных",
                date = currentTime + dayInMillis * 2
            )
        ))
    }

    fun getAllEvents(): List<Event> {
        return events.toList()
    }

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun deleteEvent(event: Event) {

    }
}