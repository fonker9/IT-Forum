package com.example.hahaton.data.repository

import com.example.hahaton.data.remote.FirebaseSpeakerDataSource

object SpeakerRepository {
    private val dataSource = FirebaseSpeakerDataSource()


    fun createTestEntries() {
        dataSource.createTestEntries()
    }
}