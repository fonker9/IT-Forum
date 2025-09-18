package com.example.hahaton.ui.event

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hahaton.R
import com.example.hahaton.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_event)

        val eventId = intent.getStringExtra("eventId")
        Log.d("test2", eventId ?: "beda")

        if (eventId != null) {
            lifecycleScope.launch {
                val event = EventRepository.getEvent(eventId)

                Log.d("test2", event?.title ?: "adadasd")
            }
        }
    }
}