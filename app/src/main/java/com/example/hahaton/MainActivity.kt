package com.example.hahaton

//для крутилки событий
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hahaton.data.repository.EventRepository
import com.example.hahaton.databinding.ActivityMainBinding
import com.example.hahaton.ui.login.LoginScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLog", "onCreate started")

        FirebaseApp.initializeApp(this)
        Log.d("MyLog", "Firebase initialized")

        val auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // ПОЛЬЗОВАТЕЛЬ АВТОРИЗОВАН - показываем основной интерфейс
            Log.d("MyLog", "uid = ${currentUser.uid}!")

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Настраиваем навигацию только для авторизованного пользователя
            val navView: BottomNavigationView = binding.navView
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navView.setupWithNavController(navController)

            lifecycleScope.launch {
                EventRepository.createTestEntries()
            }

        } else {
            // ПОЛЬЗОВАТЕЛЬ НЕ АВТОРИЗОВАН - показываем экран логина
            Log.d("MyLog", "uid = null! Showing login screen")

            setContent {
                LoginScreen(
                    onLoginSuccess = {
                        // Перезапускаем MainActivity после успешного входа
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Закрываем текущую активити
                    }
                )
            }
        }
    }
}