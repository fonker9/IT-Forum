package com.example.hahaton.data.model

import java.util.Date
import java.util.UUID

data class SubEvent(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Undefined",
    val speaker: Speaker? = null,
    val date: Date = Date(),
)
