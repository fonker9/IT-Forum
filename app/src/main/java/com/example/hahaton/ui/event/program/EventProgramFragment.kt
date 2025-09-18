package com.example.hahaton.ui.event.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahaton.data.model.Event
import com.example.hahaton.databinding.FragmentEventProgramBinding
import com.example.hahaton.ui.admin.SubEventAdapter

class EventProgramFragment(
    private var event: Event
) : Fragment() {
    private var _binding: FragmentEventProgramBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SubEventAdapter(event.subevents)

        binding.eventProgramRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.eventProgramRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}