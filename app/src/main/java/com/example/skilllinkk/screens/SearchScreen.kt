package com.example.skilllinkk.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.DummyData
import com.example.skilllinkk.components.SearchBar
import com.example.skilllinkk.components.SkillCard
import com.example.skilllinkk.firebase.SkillRepository
import com.example.skilllinkk.model.Skill

@Composable
fun SearchScreen(
    navController: NavController
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    var firestoreSkills by remember {
        mutableStateOf<List<Skill>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }


    LaunchedEffect(Unit) {

        SkillRepository.getSkills { skills ->

            firestoreSkills = skills

            isLoading = false

        }

    }


    val allSkills = remember(firestoreSkills) {

        DummyData.skills + firestoreSkills

    }


    val filteredSkills = allSkills.filter { skill ->

        if (searchQuery.isBlank()) {

            true

        } else {

            skill.title.contains(
                searchQuery,
                ignoreCase = true
            ) ||

                    skill.description.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||

                    skill.mentor.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||

                    skill.category.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||

                    skill.difficulty.contains(
                        searchQuery,
                        ignoreCase = true
                    )

        }

    }


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = 120.dp
        )

    ) {


        item {

            Text(

                text = "Discover Skills 🔍",

                style =
                    MaterialTheme.typography.headlineMedium,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(

                text = "Find the right skill and connect with mentors.",

                style =
                    MaterialTheme.typography.bodyMedium,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant

            )


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            SearchBar(

                query = searchQuery,

                onQueryChange = {

                    searchQuery = it

                }

            )


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            Text(

                text =

                    if (searchQuery.isBlank()) {

                        "Explore Skills"

                    } else {

                        "Search Results"

                    },

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

        }


        if (isLoading) {

            item {

                androidx.compose.foundation.layout.Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),

                    contentAlignment = Alignment.Center

                ) {

                    CircularProgressIndicator()

                }

            }

        } else if (filteredSkills.isEmpty()) {

            item {

                Text(

                    text = "No skills found for \"$searchQuery\" 😕",

                    style =
                        MaterialTheme.typography.bodyLarge

                )

            }

        } else {

            items(

                items = filteredSkills,

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
                    modifier = Modifier.height(16.dp)
                )

            }

        }

    }

}