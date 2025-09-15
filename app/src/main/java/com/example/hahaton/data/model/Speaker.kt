package com.example.hahaton.data.model

data class Speaker (
    val firstName: String,  // Имя
    val secondName: String, // Фамилия
    val lastName: String,   // Отчество
) {
    fun getFormattedName(): String {
        return "$firstName $secondName $lastName"
    }
}