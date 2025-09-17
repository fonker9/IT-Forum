package com.example.hahaton.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hahaton.databinding.FragmentHomeBinding
import com.example.hahaton.ui.home.events.EventsFragment
import com.example.hahaton.R
import com.example.hahaton.data.model.Event
import com.example.hahaton.ui.news.NewsFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    val fs: FirebaseFirestore = Firebase.firestore
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonEvents: Button = binding.buttonEvents
        buttonEvents.setOnClickListener {
            val fragment = EventsFragment()

            childFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_home, fragment)
                addToBackStack(null)
                commit()
            }
        }

        val buttonNews: Button = binding.buttonNews
        buttonNews.setOnClickListener {
            val fragment = NewsFragment()

            childFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_home, fragment)
                addToBackStack(null)
                commit()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}