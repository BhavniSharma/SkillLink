package com.example.skilllinkk.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.components.AppButton
import com.example.skilllinkk.components.AppTextField
import com.example.skilllinkk.navigation.Routes

@Composable
fun ForgotPasswordScreen(
    navController: NavController
) {

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter your registered email address and we'll send you a password reset link.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address"
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppButton(
            text = "Send Reset Link",
            onClick = {
                // Firebase Password Reset will be added later
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.LOGIN) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text("Back to Sign In")
        }

    }
}