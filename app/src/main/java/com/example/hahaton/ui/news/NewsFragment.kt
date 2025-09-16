package com.example.hahaton.ui.news

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahaton.R
import com.example.hahaton.databinding.FragmentHomeNewsBinding
import com.example.hahaton.ui.EventAdapter

class NewsFragment : Fragment() {
    private var _binding: FragmentHomeNewsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Данные для примера
        val events = listOf("News 1", "News 2", "News 3", "News 4", "News 5")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerNews)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter: EventAdapter = EventAdapter(events)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}