package com.example.hahaton.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.hahaton.MainActivity
import com.example.hahaton.R
import com.example.hahaton.databinding.ActivityEventAddBinding
import com.example.hahaton.ui.home.events.EventsFragment
import com.example.hahaton.ui.login.LoginScreen

class EventAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val program: Button = binding.Program
        program.setOnClickListener {
            val fragment = ProgramFragment()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }

        val speakers: Button = binding.Speakers
        speakers.setOnClickListener {
            val fragment = ProgramFragment()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }

        val partners: Button = binding.Partners
        partners.setOnClickListener {
            val fragment = ProgramFragment()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}