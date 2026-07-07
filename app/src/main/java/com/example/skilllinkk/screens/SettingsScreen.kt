package com.example.skilllinkk.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {

    val currentUser =
        FirebaseAuthManager.auth.currentUser

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Settings")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())

        ) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Text(

                text = "Account",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)

                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )


                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )


                    Column {

                        Text(
                            text = "Logged in as",
                            fontWeight = FontWeight.SemiBold
                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(

                            text =
                                currentUser?.email
                                    ?: "No email available",

                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant

                        )

                    }

                }

            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)

                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )


                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )


                    Column {

                        Text(
                            text = "Account Status",
                            fontWeight = FontWeight.SemiBold
                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(

                            text = "Active SkillLink Member",

                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant

                        )

                    }

                }

            }


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            Text(

                text = "App",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)

                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )


                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )


                    Column {

                        Text(
                            text = "About SkillLink",
                            fontWeight = FontWeight.SemiBold
                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(

                            text =
                                "Learn, teach and connect with skilled mentors.",

                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant

                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(
                            text = "Version 1.0"
                        )

                    }

                }

            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)

                ) {

                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )


                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )


                    Column {

                        Text(
                            text = "Privacy & Security",
                            fontWeight = FontWeight.SemiBold
                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(

                            text =
                                "Your account is protected with Firebase Authentication.",

                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant

                        )

                    }

                }

            }


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            OutlinedButton(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    FirebaseAuthManager.auth.signOut()

                    navController.navigate(
                        Routes.WELCOME
                    ) {

                        popUpTo(0) {
                            inclusive = true
                        }

                    }

                }

            ) {

                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null
                )


                Spacer(
                    modifier = Modifier.width(8.dp)
                )


                Text("Logout")

            }


            Spacer(
                modifier = Modifier.height(30.dp)
            )

        }

    }

}