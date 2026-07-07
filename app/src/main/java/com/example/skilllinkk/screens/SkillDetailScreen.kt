package com.example.skilllinkk.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.DummyData
import com.example.skilllinkk.FavoritesManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.model.Skill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillDetailScreen(
    navController: NavController,
    skillId: String
) {

    val context = LocalContext.current

    var skill by remember {
        mutableStateOf<Skill?>(null)
    }

    var isLoadingSkill by remember {
        mutableStateOf(true)
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }

    var isFavoriteLoading by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(skillId) {

        val dummySkill = DummyData.skills.find {
            it.id == skillId
        }


        if (dummySkill != null) {

            skill = dummySkill

            isFavorite =
                FavoritesManager.isFavorite(dummySkill)

            isLoadingSkill = false


            FavoritesManager.loadFavorites(

                onSuccess = {

                    isFavorite =
                        FavoritesManager.isFavorite(
                            dummySkill
                        )

                },

                onFailure = {

                    isFavorite =
                        FavoritesManager.isFavorite(
                            dummySkill
                        )

                }

            )

        } else {

            FirestoreManager.db
                .collection("skills")
                .document(skillId)
                .get()
                .addOnSuccessListener { document ->

                    val firestoreSkill =
                        document.toObject(
                            Skill::class.java
                        )


                    skill = firestoreSkill

                    isLoadingSkill = false


                    if (firestoreSkill != null) {

                        FavoritesManager.loadFavorites(

                            onSuccess = {

                                isFavorite =
                                    FavoritesManager
                                        .isFavorite(
                                            firestoreSkill
                                        )

                            },

                            onFailure = {

                                isFavorite =
                                    FavoritesManager
                                        .isFavorite(
                                            firestoreSkill
                                        )

                            }

                        )

                    }

                }
                .addOnFailureListener { exception ->

                    isLoadingSkill = false


                    Toast.makeText(
                        context,
                        exception.message
                            ?: "Failed to load skill",
                        Toast.LENGTH_LONG
                    ).show()

                }

        }

    }


    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = skill?.title
                            ?: "Skill Details"
                    )

                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()

                        }

                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { padding ->


        if (isLoadingSkill) {

            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentAlignment = Alignment.Center

            ) {

                CircularProgressIndicator()

            }

        } else if (skill == null) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)

            ) {

                Text(

                    text = "Skill not found 😕",

                    style =
                        MaterialTheme.typography
                            .headlineMedium,

                    fontWeight = FontWeight.Bold

                )

            }

        } else {

            val currentSkill = skill!!


            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )

            ) {


                Text(

                    text = currentSkill.title,

                    style =
                        MaterialTheme.typography
                            .headlineMedium,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = "⭐ ${currentSkill.rating}"
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Person,

                        contentDescription = null

                    )


                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )


                    Text(

                        text =
                            currentSkill.mentor.ifBlank {

                                "Unknown Mentor"

                            }

                    )

                }


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Text(

                    text = "Description",

                    style =
                        MaterialTheme.typography
                            .titleLarge,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text =
                        currentSkill.description
                )


                Spacer(
                    modifier = Modifier.height(24.dp)
                )


                Text(
                    text =
                        "Category : ${currentSkill.category}"
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text =
                        "Difficulty : ${currentSkill.difficulty}"
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text =
                        "Learners : ${currentSkill.learners}"
                )


                Spacer(
                    modifier = Modifier.height(30.dp)
                )


                Button(

                    modifier =
                        Modifier.fillMaxWidth(),

                    enabled = !isFavoriteLoading,

                    onClick = {

                        isFavoriteLoading = true


                        if (isFavorite) {

                            FavoritesManager.removeSkill(

                                skill = currentSkill,

                                onSuccess = {

                                    isFavoriteLoading =
                                        false

                                    isFavorite = false


                                    Toast.makeText(
                                        context,
                                        "Removed from Favorites",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                },

                                onFailure = { exception ->

                                    isFavoriteLoading =
                                        false


                                    Toast.makeText(
                                        context,
                                        exception.message
                                            ?: "Failed to remove favorite",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                            )

                        } else {

                            FavoritesManager.addSkill(

                                skill = currentSkill,

                                onSuccess = {

                                    isFavoriteLoading =
                                        false

                                    isFavorite = true


                                    Toast.makeText(
                                        context,
                                        "Added to Favorites ❤️",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                },

                                onFailure = { exception ->

                                    isFavoriteLoading =
                                        false


                                    Toast.makeText(
                                        context,
                                        exception.message
                                            ?: "Failed to add favorite",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                            )

                        }

                    }

                ) {


                    if (isFavoriteLoading) {

                        CircularProgressIndicator(

                            modifier =
                                Modifier.size(22.dp),

                            strokeWidth = 2.dp

                        )

                    } else {

                        Text(

                            text =

                                if (isFavorite) {

                                    "❤️ Remove from Favorites"

                                } else {

                                    "🤍 Save to Favorites"

                                }

                        )

                    }

                }


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                OutlinedButton(

                    modifier =
                        Modifier.fillMaxWidth(),

                    onClick = {

                        if (
                            currentSkill
                                .mentorEmail
                                .isBlank()
                        ) {

                            Toast.makeText(
                                context,
                                "Mentor email is not available",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@OutlinedButton

                        }


                        val subject =

                            "Interested in ${currentSkill.title}"


                        val intent = Intent(
                            Intent.ACTION_SENDTO
                        ).apply {

                            data =
                                Uri.parse("mailto:")


                            putExtra(

                                Intent.EXTRA_EMAIL,

                                arrayOf(
                                    currentSkill.mentorEmail
                                )

                            )


                            putExtra(

                                Intent.EXTRA_SUBJECT,

                                subject

                            )

                        }


                        try {

                            context.startActivity(intent)

                        } catch (
                            exception: Exception
                        ) {

                            Toast.makeText(
                                context,
                                "No email app found",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }

                ) {

                    Text(
                        text = "📩 Contact Mentor"
                    )

                }


                Spacer(
                    modifier = Modifier.height(30.dp)
                )

            }

        }

    }

}