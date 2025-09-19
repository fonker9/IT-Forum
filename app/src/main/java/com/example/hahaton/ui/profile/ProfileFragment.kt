package com.example.hahaton.ui.profile

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.ViewModelProvider
import com.example.hahaton.MainActivity
import com.example.hahaton.databinding.FragmentProfileBinding
import com.example.hahaton.ui.login.LoginActivity // Создайте этот Activity
import com.example.hahaton.ui.login.LoginScreen
import com.example.hahaton.ui.login.RegisterActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val LogOut: Button = binding.logOut
        LogOut.setOnClickListener {
            signOut(auth)
            Log.d("MyLog", "Log Out is complete!")
        val exit: Button = binding.exit
            exit.setOnClickListener {
                // Закрытие приложения // Закрывает все активности приложения
                System.exit(0)   // Полностью завершает процесс
            }
            // Переход к Activity с Compose
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }


        return root
    }

    private fun signOut(auth: FirebaseAuth) {
        auth.signOut()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}