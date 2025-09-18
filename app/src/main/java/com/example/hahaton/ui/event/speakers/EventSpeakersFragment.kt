package com.example.hahaton.ui.event.speakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahaton.data.model.Event
import com.example.hahaton.databinding.FragmentEventSpeakersBinding

class EventSpeakersFragment(
    private val event: Event
) : Fragment() {
    private var _binding: FragmentEventSpeakersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventSpeakersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventSpeakerAdapter()

        adapter.submitList(event.getSpeakers())

        binding.eventSpeakersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.eventSpeakersRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}