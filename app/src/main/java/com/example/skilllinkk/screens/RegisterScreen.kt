package com.example.skilllinkk.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
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
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.model.User
import com.example.skilllinkk.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavController
) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Join SkillLink and start learning today.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppButton(
            text = "Create Account",
            onClick = {

                when {

                    fullName.isBlank() ||
                            email.isBlank() ||
                            password.isBlank() ||
                            confirmPassword.isBlank() -> {

                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    password != confirmPassword -> {

                        Toast.makeText(
                            context,
                            "Passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    password.length < 6 -> {

                        Toast.makeText(
                            context,
                            "Password must be at least 6 characters",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {

                        FirebaseAuthManager.auth
                            .createUserWithEmailAndPassword(
                                email.trim(),
                                password
                            )
                            .addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    val firebaseUser =
                                        FirebaseAuthManager.auth.currentUser

                                    if (firebaseUser != null) {

                                        val user = User(
                                            uid = firebaseUser.uid,
                                            fullName = fullName,
                                            email = email,
                                            phone = "",
                                            bio = "",
                                            role = "Learner",
                                            imageUrl = ""
                                        )

                                        FirestoreManager.db
                                            .collection("users")
                                            .document(firebaseUser.uid)
                                            .set(user)
                                            .addOnSuccessListener {

                                                Toast.makeText(
                                                    context,
                                                    "Account Created Successfully 🎉",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                navController.navigate(Routes.HOME) {
                                                    popUpTo(Routes.WELCOME) {
                                                        inclusive = true
                                                    }
                                                }

                                            }
                                            .addOnFailureListener { e ->

                                                Log.e("FIRESTORE", "Save Failed", e)

                                                Toast.makeText(
                                                    context,
                                                    e.localizedMessage ?: "Firestore Error",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                            }

                                    }

                                } else {

                                    Toast.makeText(
                                        context,
                                        task.exception?.localizedMessage
                                            ?: "Registration Failed",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                            }

                    }

                }

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppOutlinedButton(
            text = "Continue with Google",
            onClick = {
                // Google Sign-In later
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {

            Text("Already have an account? ")

            TextButton(
                onClick = {
                    navController.navigate(Routes.LOGIN)
                }
            ) {
                Text("Sign In")
            }

        }

    }
}