package com.example.hahaton.data.model

import java.util.UUID

data class Speaker (
    val id: String = UUID.randomUUID().toString(),
    val firstName: String = "Иван",  // Имя
    val secondName: String = "Иванов", // Фамилия
    val patronymic: String = "Иванович",   // Отчество
)