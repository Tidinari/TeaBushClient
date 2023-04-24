package ru.tidinari.teabush.ui.screen.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.tidinari.teabush.ui.navigation.Screen
import ru.tidinari.teabush.ui.shared.singletonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = singletonViewModel(),
    navigationController: NavHostController,
) {
    val context: Context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAlreadyRegistered by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val isLoginButtonEnabled: Boolean = login.isNotBlank() && password.isNotBlank() && !isLoading

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isAlreadyRegistered) "Регистрация" else "Авторизация",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = login,
            enabled = !isLoading,
            label = { Text(text = "Логин") },
            onValueChange = { login = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            enabled = !isLoading,
            label = { Text(text = "Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Уже зарегистрированы?", style = MaterialTheme.typography.bodySmall)
            Checkbox(checked = isAlreadyRegistered, onCheckedChange = { isAlreadyRegistered = it })
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = isLoginButtonEnabled,
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    delay(1000)
                    isLoading = false
                    Toast.makeText(context, "Успешный вход!", Toast.LENGTH_SHORT).show()
                    navigationController.navigate(Screen.Profile.route)
                }
            }
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
            else Text(text = if (isAlreadyRegistered) "Зарегистрироваться" else "Войти")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthPreview() {
    AuthScreen(
        authViewModel = AuthViewModel(),
        navigationController = rememberNavController()
    )
}