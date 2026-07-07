package com.example.skilllinkk.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.components.AppButton
import com.example.skilllinkk.components.AppOutlinedButton
import com.example.skilllinkk.components.AppTextField
import com.example.skilllinkk.components.PasswordField
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome Back",

            style = MaterialTheme.typography.headlineMedium,

            fontWeight = FontWeight.Bold,

            color = MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "Sign in to continue learning and sharing skills.",

            style = MaterialTheme.typography.bodyMedium
        )


        Spacer(
            modifier = Modifier.height(40.dp)
        )


        AppTextField(
            value = email,

            onValueChange = {
                email = it
            },

            label = "Email"
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        PasswordField(
            value = password,

            onValueChange = {
                password = it
            }
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.End
        ) {

            TextButton(
                enabled = !isLoading,

                onClick = {

                    navController.navigate(
                        Routes.FORGOT_PASSWORD
                    )

                }
            ) {

                Text(
                    text = "Forgot Password?"
                )

            }

        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        if (isLoading) {

            CircularProgressIndicator()

        } else {

            AppButton(
                text = "Sign In",

                onClick = {

                    val cleanEmail =
                        email.trim()


                    if (
                        cleanEmail.isBlank() ||
                        password.isBlank()
                    ) {

                        Toast.makeText(
                            context,
                            "Please enter email and password",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@AppButton

                    }


                    isLoading = true


                    FirebaseAuthManager.auth
                        .signInWithEmailAndPassword(
                            cleanEmail,
                            password
                        )
                        .addOnSuccessListener {

                            isLoading = false


                            Toast.makeText(
                                context,
                                "Login Successful 🎉",
                                Toast.LENGTH_SHORT
                            ).show()


                            navController.navigate(
                                Routes.HOME
                            ) {

                                popUpTo(0) {
                                    inclusive = true
                                }

                                launchSingleTop = true

                            }

                        }
                        .addOnFailureListener { exception ->

                            isLoading = false


                            Toast.makeText(
                                context,
                                exception.message
                                    ?: "Login Failed",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                }
            )

        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        AppOutlinedButton(
            text = "Continue with Google",

            onClick = {

                Toast.makeText(
                    context,
                    "Google Sign-In coming soon",
                    Toast.LENGTH_SHORT
                ).show()

            }
        )


        Spacer(
            modifier = Modifier.height(28.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Don't have an account? "
            )


            TextButton(
                enabled = !isLoading,

                onClick = {

                    navController.navigate(
                        Routes.REGISTER
                    )

                }
            ) {

                Text(
                    text = "Register"
                )

            }

        }

    }

}