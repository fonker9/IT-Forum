package com.example.hahaton.data.model

import java.util.Date

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val speakers: List<Speaker>?
)