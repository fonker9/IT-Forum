package com.example.hahaton.data.model

import java.util.Date

data class SubEvent(
    val id: String,
    val title: String,
    val speaker: Speaker,
    val date: Date,
)
