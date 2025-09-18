package com.example.hahaton.ui.home

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hahaton.databinding.FragmentHomeBinding
import com.example.hahaton.ui.home.events.EventsFragment
import com.example.hahaton.R
import com.example.hahaton.data.model.Event
import com.example.hahaton.ui.admin.EventAddActivity
import com.example.hahaton.ui.news.NewsFragment
import com.google.firebase.auth.FirebaseAuth
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

        replaceFragment(EventsFragment())

        // Цвета
        val purpleColor = Color.parseColor("#5B00A3")
        val blueColor = Color.parseColor("#5557FE")

        // Устанавливаем начальное состояние (например, events выбран)

        val buttonNews: Button = binding.buttonNews
        val buttonEvents: Button = binding.buttonEvents
        buttonEvents.setOnClickListener { replaceFragment(EventsFragment())
            buttonEvents.backgroundTintList = ColorStateList.valueOf(purpleColor)
            buttonNews.backgroundTintList = ColorStateList.valueOf(blueColor)
        }
        buttonEvents.backgroundTintList = ColorStateList.valueOf(purpleColor)
        buttonNews.backgroundTintList = ColorStateList.valueOf(blueColor)

        buttonNews.setOnClickListener { replaceFragment(NewsFragment())
            buttonNews.backgroundTintList = ColorStateList.valueOf(purpleColor)
            buttonEvents.backgroundTintList = ColorStateList.valueOf(blueColor)}

        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Кнопка редактирования мероприятий

        val containerAdmin: ConstraintLayout = view.findViewById(R.id.admin)
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser // Проверяем текущего пользователя
        if (currentUser != null && (currentUser.uid == "a0W1CVqGozOCp7UbFlqDS7ytLqj1" || currentUser.uid == "4VShLEA5gvb22itEmke3ngv7Sxb2" ) ) {
            containerAdmin.visibility = View.VISIBLE // Пользователь с нужным UID, показываем кнопку

        } else {
            containerAdmin.visibility = View.GONE // Другой пользователь или пользователь не авторизован, скрываем кнопку
        }

        val buttonAdmin: Button = binding.buttonAdmin
        buttonAdmin.setOnClickListener {
            val intent = Intent(requireContext(), EventAddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_home, fragment)
            .commit()
    }
}