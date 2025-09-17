package com.example.hahaton.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import java.util.UUID

@IgnoreExtraProperties
data class Event(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Undefined",
) {
    @Exclude
    var subevents: MutableList<SubEvent> = ArrayList()

    @Exclude
    fun getStartDate(): Date {
        val subevent = subevents.first()

        return subevent.date
    }

    @Exclude
    fun getEndDate(): Date {
        val subevent = subevents.last()

        return subevent.date
    }
}