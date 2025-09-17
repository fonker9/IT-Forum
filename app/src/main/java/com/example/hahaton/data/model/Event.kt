package com.example.hahaton.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date

@IgnoreExtraProperties
data class Event(
    val id: String ="",
    val title: String ="",
) {
    @Exclude
    var subevents: List<SubEvent> = listOf()

    fun getStartDate(): Date {
        val subevent = subevents.first()

        return subevent.date
    }

    fun getEndDate(): Date {
        val subevent = subevents.last()

        return subevent.date
    }
}