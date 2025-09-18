package com.example.hahaton.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val auth = Firebase.auth
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Градиент для кнопки (розовый → синий сверху вниз)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFF1493), // Ярко-розовый
            Color(0xFF4169E1)  // Королевский синий
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок "Авторизация" жирным
        Text(
            text = "Авторизация",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

//        // Кнопка "Регистрация" синим цветом с подчеркиванием
//        TextButton(
//            onClick = onNavigateToRegister,
//            modifier = Modifier.padding(0.dp),
//
//
//        ) {
//            Text(
//                text = "Регистрация",
//                color = MaterialTheme.colorScheme.primary,
//                fontSize = 16.sp,
//                textDecoration = TextDecoration.Underline,
//
//            )
//        }
        Text(
            text = "Регистрация",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6C63FF),
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline,
//            modifier = Modifier.clickable { onNavigateToRegister() }
            modifier = Modifier.clickable(onClick = onNavigateToRegister)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Надпись "Добро пожаловать!"
        Text(
            text = "Добро пожаловать!",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Поле для email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFE9E9ED), // #E9E9ED
                focusedContainerColor = Color(0xFFE9E9ED),   // #E9E9ED
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent, // Убираем индикатор
                focusedIndicatorColor = Color.Transparent,   // Убираем индикатор
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для пароля
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFE9E9ED), // #E9E9ED
                focusedContainerColor = Color(0xFFE9E9ED),   // #E9E9ED
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent, // Убираем индикатор
                focusedIndicatorColor = Color.Transparent,   // Убираем индикатор
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Войти" с градиентом и закругленными углами
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isLoading = true
                    errorMessage = null
                    signIn(auth, email, password) {
                        isLoading = false
                        onLoginSuccess()
                    }
                } else {
                    errorMessage = "Заполните все поля"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp), // Закругленные углы
            contentPadding = PaddingValues(0.dp), // Убираем внутренние отступы кнопки
            elevation = null // Убираем тень
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF6C63FF), Color(0xFF9C27B0))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Войти",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

private fun signIn(auth: FirebaseAuth, email: String, password: String, onSuccess: () -> Unit) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("MyLog", "Sign In successful!")
                onSuccess()
            } else {
                Log.d("MyLog", "Sign In failure: ${task.exception?.message}")
            }
        }
}
