
package com.example.hahaton.ui.register

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val auth = Firebase.auth
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Регистрация", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Подтвердите пароль") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (password == confirmPassword) {
                signUp(auth, email, password) {
                    onRegisterSuccess()
                }
            } else {
                errorMessage = "Пароли не совпадают"
            }
        }) {
            Text(text = "Зарегистрироваться")
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = { onNavigateToLogin() }) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}

private fun signUp(auth: FirebaseAuth, email: String, password: String, onSuccess: () -> Unit) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("MyLog", "Sign Up successful!")
                onSuccess()
            } else {
                Log.d("MyLog", "Sign Up failure!")
            }
        }
}
