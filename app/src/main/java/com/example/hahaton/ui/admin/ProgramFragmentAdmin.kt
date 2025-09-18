package com.example.hahaton.ui.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahaton.data.model.SubEvent
import com.example.hahaton.data.model.SubEventType
import com.example.hahaton.databinding.FragmentProgramBinding
import com.example.hahaton.ui.admin.SubEventAdapter
import java.util.Date

class ProgramFragmentAdmin : Fragment() {

    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SubEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val events = listOf(
            SubEvent(title = "Открытие хакатона", place = "Актовый зал", type = SubEventType.exhibition, dateStart = Date()),
            SubEvent(title = "Открытие хакатона2", place = "Актовый зал2", type = SubEventType.meeting, dateStart = Date()),
            SubEvent(title = "Открытие хакатона3", place = "Актовый зал3", type = SubEventType.exhibition, dateStart = Date()),
            SubEvent(title = "Открытие хакатона4", place = "Актовый зал4", type = SubEventType.exhibition, dateStart = Date()),
            SubEvent(title = "Открытие хакатона5", place = "Актовый зал5", type = SubEventType.exhibition, dateStart = Date()),

        )

        adapter = SubEventAdapter(events)
        binding.recyclerEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerEvents.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
