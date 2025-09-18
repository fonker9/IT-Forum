package com.example.hahaton.ui.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahaton.data.model.SubEvent
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
            SubEvent(title = "Открытие хакатона", place = "Актовый зал", type = "Презентация", date = Date()),
            SubEvent(title = "Workshop по Android", place = "Лаборатория 101", type = "Практикум", date = Date()),
            SubEvent(title = "Coffee Break", place = "Холл", type = "Перерыв", date = Date()),
            SubEvent(title = "Финальные проекты", place = "Актовый зал", type = "Презентация", date = Date()),
            SubEvent(title = "Награждение", place = "Сцена", type = "Закрытие", date = Date()),
            SubEvent(title = "пример1", place = "Сцена", type = "Закрытие", date = Date()),
            SubEvent(title = "пример2", place = "Сцена", type = "Закрытие", date = Date())
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
