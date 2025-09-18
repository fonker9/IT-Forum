package com.example.hahaton.data.model

import com.google.firebase.firestore.Exclude
import java.util.UUID

data class Speaker (
    val id: String = UUID.randomUUID().toString(),
    val firstName: String = "Иван",  // Имя
    val secondName: String = "Иванов", // Фамилия
    val patronymic: String = "Иванович",   // Отчество
    val description: String = "Undefined",
    val photo: String = "undefined",
) {
    @Exclude
    fun getFormattedName(): String {
        return "$secondName $firstName"
    }
}