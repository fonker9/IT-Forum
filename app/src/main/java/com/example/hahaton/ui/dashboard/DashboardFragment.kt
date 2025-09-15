package com.example.hahaton.ui.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahaton.data.repository.EventStorage
import com.example.hahaton.databinding.FragmentDashboardBinding
import com.example.hahaton.ui.events.EventsAdapter
import java.util.Timer
import kotlin.concurrent.schedule

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleViewer()
        loadEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycleViewer() {
        adapter = EventsAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DashboardFragment.adapter
            itemAnimator = DefaultItemAnimator().apply {
                addDuration = 200
                removeDuration = 200
            }
        }
    }

    private fun showEventsList() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun loadEvents() {
        val events = EventStorage.getAllEvents()

        showEventsList()
        adapter.submitList(events)

        Timer().schedule(2000, {
            EventStorage.deleteEvent(events.first())

            adapter.submitList(EventStorage.getAllEvents())
        })
    }
}