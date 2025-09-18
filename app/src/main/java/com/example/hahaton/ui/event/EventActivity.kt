package com.example.hahaton.ui.event

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private var selectedButton: Button? = null

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
                    hightlightButton(binding.eventNavigationProgram)

                    binding.eventNavigationProgram.setOnClickListener {
                        replaceFragment(EventProgramFragment(event))
                        hightlightButton(binding.eventNavigationProgram)
                    }
                    binding.eventNavigationSpeakers.setOnClickListener {
                        replaceFragment(EventSpeakersFragment(event))
                        hightlightButton(binding.eventNavigationSpeakers)
                    }

//                    TODO: make dreams come true (create NavigationView with buttons)
//                    binding.eventNavigationView.setOnItemSelectedListener { item ->
//                        when (item.itemId) {
//                            R.id.nav_event_program -> replaceFragment(EventProgramFragment(event))
//                            R.id.nav_event_speakers -> replaceFragment(EventSpeakersFragment(event))
//                            else -> true
//                        }
//
//                        true
//                    }
                }
            }
        }
    }

    private fun hightlightButton(button: Button) {
        selectedButton?.setBackgroundColor(ContextCompat.getColor(this, R.color.nav_button_idle))
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.nav_button_selected))
        selectedButton = button
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_event, fragment)
            .commit()
    }
}