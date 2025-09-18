
package com.example.hahaton.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.hahaton.MainActivity
import com.example.hahaton.ui.login.LoginScreen
import com.example.hahaton.ui.register.RegisterScreen
import com.google.firebase.FirebaseApp

// RegisterActivity.kt
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            RegisterScreen(
                onRegisterSuccess = {
                    // После успешной регистрации переходим на главный экран БЕЗ анимации
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    finish()
                },
                onNavigateToLogin = {
                    // Возвращаемся к экрану логина БЕЗ анимации
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    finish()
                }
            )
        }
    }

    // Также отключаем анимацию при нажатии кнопки назад
    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}