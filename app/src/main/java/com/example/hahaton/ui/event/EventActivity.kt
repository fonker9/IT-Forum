package com.example.hahaton.ui.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.hahaton.R
import com.example.hahaton.data.repository.EventRepository
import com.example.hahaton.databinding.ActivityEventBinding
import com.example.hahaton.ui.event.program.EventProgramFragment
import com.example.hahaton.ui.event.speakers.EventSpeakersFragment
import kotlinx.coroutines.launch

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventId = intent.getStringExtra("eventId")

        if (eventId != null) {
            lifecycleScope.launch {
                val event = EventRepository.getEvent(eventId)

                if (event != null) {
                    binding.eventTitle.text = event.title
                    binding.eventLocationText.text = event.place

                    replaceFragment(EventProgramFragment(event))

                    binding.eventNavigationView.setOnItemSelectedListener { item ->
                        when (item.itemId) {
                            R.id.nav_event_program -> replaceFragment(EventProgramFragment(event))
                            R.id.nav_event_speakers -> replaceFragment(EventSpeakersFragment(event))
                            else -> false
                        }

                        true
                    }
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_event, fragment)
            .commit()
    }
}