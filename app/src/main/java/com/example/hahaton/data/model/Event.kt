package com.example.hahaton.data.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import java.util.UUID

@IgnoreExtraProperties
data class Event(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Undefined",
    val place: String = "Undefined",
) {
    @Exclude
    var subevents: MutableList<SubEvent> = ArrayList()

    @Exclude
    fun getStartDate(): Date {
//        if (subevents.isEmpty()) return Date()

        val subevent = subevents.first()

        return subevent.dateStart
    }

    @Exclude
    fun getEndDate(): Date {
//        if (subevents.isEmpty()) return Date()

        val subevent = subevents.last()

        return subevent.dateEnd
    }

    @Exclude
    fun getSpeakers(): List<Speaker> {
        val result: HashSet<Speaker> = hashSetOf()

        for (subevent in subevents) {
            if (subevent.speaker == null) continue

            result.add(subevent.speaker)
        }

        return result.toList()
    }
}