package com.example.hahaton.data.model

data class Event(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val description: String = "",
    val date: Long = System.currentTimeMillis(),

    val speakers: List<Speaker>? = null,
) {
    fun getFormattedDate(): String {
        return "15.09.2025"
    }
}