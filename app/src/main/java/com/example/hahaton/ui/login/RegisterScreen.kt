package com.example.hahaton.ui.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val auth = Firebase.auth

    var fio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Участник") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Ссылка на авторизацию
        Text(
            text = "Авторизация",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6C63FF),
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { onNavigateToLogin() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Заполните форму, чтобы создать ваш аккаунт",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Поле ФИО
        OutlinedTextField(
            value = fio,
            onValueChange = { fio = it },
            placeholder = { Text("Фамилия Имя Отчество") },
            modifier = Modifier.fillMaxWidth(),shape = RoundedCornerShape(12.dp),
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

        Spacer(modifier = Modifier.height(12.dp))

        // Поле Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(12.dp))

        // Поле Пароль
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(12.dp))

        // Подтверждение пароля
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Подтвердить пароль") },
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(20.dp))

//        // Радио кнопки
//        Text(text = "Вы:", fontSize = 14.sp, modifier = Modifier.align(Alignment.Start))
//        Spacer(modifier = Modifier.height(6.dp))
//
//        val roles = listOf("Участник", "Спикер", "Организатор")
//        roles.forEach { item ->
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                RadioButton(
//                    selected = role == item,
//                    onClick = { role = item }
//                )
//                Text(text = item)
//            }
//        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp) // Убираем отступ слева
        ) {
            Text(
                text = "Вы:",
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(6.dp))

            val roles = listOf("Участник", "Спикер", "Организатор")
            roles.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp) // Убираем отступ слева
                ) {
                    RadioButton(
                        selected = role == item,
                        onClick = { role = item },
                        modifier = Modifier.padding(start = 0.dp) // Убираем отступ у радиокнопки
                    )
                    Text(
                        text = item,
                        modifier = Modifier.padding(start = 4.dp) // Небольшой отступ между кнопкой и текстом
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Градиентная кнопка
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFF6C63FF), Color(0xFF9C27B0))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    if (password == confirmPassword) {
                        signUp(auth, email, password) {
                            onRegisterSuccess()
                        }
                    } else {
                        errorMessage = "Пароли не совпадают"
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Зарегистрироваться", color = Color.White, fontSize = 16.sp)
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
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
