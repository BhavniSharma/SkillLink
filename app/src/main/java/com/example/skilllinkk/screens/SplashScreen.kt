package com.example.skilllinkk.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.components.AppLogo
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {

        delay(2000)

        if (FirebaseAuthManager.auth.currentUser != null) {

            navController.navigate(Routes.HOME) {

                popUpTo(Routes.SPLASH) {
                    inclusive = true
                }

            }

        } else {

            navController.navigate(Routes.WELCOME) {

                popUpTo(Routes.SPLASH) {
                    inclusive = true
                }

            }

        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppLogo()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "SkillLink",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Learn • Teach • Connect",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(40.dp))

        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )

    }

}