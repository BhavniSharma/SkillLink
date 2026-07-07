package com.example.skilllinkk.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.FavoritesManager
import com.example.skilllinkk.components.ProfileStatCard
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.model.User
import com.example.skilllinkk.navigation.Routes

@Composable
fun ProfileScreen(
    navController: NavController
) {

    var user by remember {
        mutableStateOf(User())
    }

    var mySkillsCount by remember {
        mutableIntStateOf(0)
    }


    LaunchedEffect(Unit) {

        val uid =
            FirebaseAuthManager.auth.currentUser?.uid


        if (uid != null) {

            FirestoreManager.db
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->

                    document
                        .toObject(User::class.java)
                        ?.let {

                            user = it

                        }

                }


            FirestoreManager.db
                .collection("skills")
                .whereEqualTo(
                    "createdBy",
                    uid
                )
                .get()
                .addOnSuccessListener { documents ->

                    mySkillsCount =
                        documents.size()

                }

        }

    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Box(

            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primary
                ),

            contentAlignment = Alignment.Center

        ) {

            Icon(

                imageVector =
                    Icons.Default.Person,

                contentDescription = null,

                tint = Color.White,

                modifier = Modifier.size(60.dp)

            )

        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(

            text =
                user.fullName.ifBlank {
                    "User"
                },

            style =
                MaterialTheme.typography.headlineMedium,

            fontWeight = FontWeight.Bold

        )


        Spacer(
            modifier = Modifier.height(6.dp)
        )


        Text(

            text = user.role,

            style =
                MaterialTheme.typography.bodyLarge,

            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant

        )


        Spacer(
            modifier = Modifier.height(6.dp)
        )


        Text(

            text = user.email,

            style =
                MaterialTheme.typography.bodyMedium,

            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant

        )


        if (user.phone.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(

                text = "📞 ${user.phone}",

                style =
                    MaterialTheme.typography.bodyMedium

            )

        }


        if (user.bio.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Card(

                modifier = Modifier.fillMaxWidth()

            ) {

                Column(

                    modifier =
                        Modifier.padding(16.dp)

                ) {

                    Text(

                        text = "About Me",

                        fontWeight = FontWeight.Bold

                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(
                        text = user.bio
                    )

                }

            }

        }


        Spacer(
            modifier = Modifier.height(26.dp)
        )


        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceEvenly

        ) {


            ProfileStatCard(

                title = "Favorites",

                value =
                    FavoritesManager
                        .favoriteSkills
                        .size
                        .toString()

            )


            ProfileStatCard(

                title = "Skills",

                value =
                    mySkillsCount.toString()

            )


            ProfileStatCard(

                title = "Rating",

                value = "4.9"

            )

        }


        Spacer(
            modifier = Modifier.height(36.dp)
        )


        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                navController.navigate(
                    Routes.EDIT_PROFILE
                )

            }

        ) {

            Icon(

                imageVector =
                    Icons.Default.Edit,

                contentDescription = null

            )


            Spacer(
                modifier = Modifier.width(8.dp)
            )


            Text("Edit Profile")

        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        OutlinedButton(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                navController.navigate(
                    Routes.SETTINGS
                )

            }

        ) {

            Icon(

                imageVector =
                    Icons.Default.Settings,

                contentDescription = null

            )


            Spacer(
                modifier = Modifier.width(8.dp)
            )


            Text("Settings")

        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        OutlinedButton(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                FavoritesManager.clearFavorites()

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

                imageVector =
                    Icons.Default.ExitToApp,

                contentDescription = null

            )


            Spacer(
                modifier = Modifier.width(8.dp)
            )


            Text("Logout")

        }

    }

}