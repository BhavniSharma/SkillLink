package com.example.skilllinkk.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.FavoritesManager
import com.example.skilllinkk.components.SkillCard

@Composable
fun FavoritesScreen(
    navController: NavController
) {

    val favoriteSkills =
        FavoritesManager.favoriteSkills


    if (favoriteSkills.isEmpty()) {

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            verticalArrangement =
                Arrangement.Center

        ) {

            item {

                Text(

                    text = "❤️ Favorites",

                    style =
                        MaterialTheme.typography
                            .headlineMedium,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Text(

                    text =
                        "You haven't saved any skills yet."

                )

            }

        }

    } else {

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            contentPadding =
                PaddingValues(
                    vertical = 20.dp
                )

        ) {

            item {

                Text(

                    text = "❤️ Favorites",

                    style =
                        MaterialTheme.typography
                            .headlineMedium,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )

            }


            items(

                items = favoriteSkills,

                key = { skill ->
                    skill.id
                }

            ) { skill ->


                SkillCard(

                    title = skill.title,

                    subtitle = skill.description,

                    mentor = skill.mentor,

                    learners =
                        "${skill.learners} Learners",

                    rating =
                        skill.rating.toString(),

                    onClick = {

                        navController.navigate(
                            "skillDetail/${skill.id}"
                        )

                    }

                )


                Spacer(
                    modifier = Modifier.height(14.dp)
                )

            }

        }

    }

}