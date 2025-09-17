package com.example.hahaton.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hahaton.MainActivity
import com.example.hahaton.R
import com.example.hahaton.databinding.ActivityEventAddBinding
import com.example.hahaton.ui.events.EventsFragment
import com.example.hahaton.ui.login.LoginScreen

class EventAddActivity : ComponentActivity(){
    private lateinit var binding: ActivityEventAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val program: Button = binding.Program
        program.setOnClickListener {
            val fragment = EventsFragment()

        }

    }
}