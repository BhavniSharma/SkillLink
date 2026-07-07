package com.example.skilllinkk.screens

import java.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.skilllinkk.FavoritesManager
import com.example.skilllinkk.components.CategoryChip
import com.example.skilllinkk.components.FeaturedSkillCard
import com.example.skilllinkk.components.LearningProgressCard
import com.example.skilllinkk.components.MentorCard
import com.example.skilllinkk.components.SearchBar
import com.example.skilllinkk.components.SkillCard
import com.example.skilllinkk.components.WelcomeCard
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.firebase.SkillRepository
import com.example.skilllinkk.model.Skill
import com.example.skilllinkk.model.User


fun getGreeting(): String {

    val hour = Calendar
        .getInstance()
        .get(Calendar.HOUR_OF_DAY)

    return when {

        hour in 5..11 ->
            "Good Morning ☀️"

        hour in 12..16 ->
            "Good Afternoon 🌤️"

        hour in 17..20 ->
            "Good Evening 🌆"

        else ->
            "Good Night 🌙"

    }

}


fun getSubtitle(): String {

    val hour = Calendar
        .getInstance()
        .get(Calendar.HOUR_OF_DAY)

    return when {

        hour in 5..11 ->
            "Start your day by learning something new! 🚀"

        hour in 12..16 ->
            "Keep your learning streak alive! 📚"

        hour in 17..20 ->
            "A perfect evening to build new skills! 💡"

        else ->
            "End your day with a little learning! 🌙"

    }

}


@Composable
fun HomeScreen(
    navController: NavController,
    padding: PaddingValues
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    var user by remember {
        mutableStateOf(User())
    }

    var firestoreSkills by remember {
        mutableStateOf<List<Skill>>(emptyList())
    }

    var isLoadingSkills by remember {
        mutableStateOf(true)
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

                    val firestoreUser =
                        document.toObject(
                            User::class.java
                        )

                    if (firestoreUser != null) {

                        user = firestoreUser

                    }

                }

        }


        SkillRepository.getSkills { skills ->

            firestoreSkills = skills

            isLoadingSkills = false

        }

    }


    val allSkills = remember(
        firestoreSkills
    ) {

        DummyData.skills + firestoreSkills

    }


    val filteredSkills = allSkills.filter { skill ->

        val matchesSearch =

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
                    )


        val matchesCategory =

            selectedCategory == "All" ||

                    skill.category.equals(
                        selectedCategory,
                        ignoreCase = true
                    )


        matchesSearch && matchesCategory

    }


    val currentUserId =
        FirebaseAuthManager.auth.currentUser?.uid


    val myPostedSkillsCount = firestoreSkills.count { skill ->

        skill.createdBy == currentUserId

    }


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 20.dp),

        contentPadding = PaddingValues(
            bottom = 24.dp
        )

    ) {


        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )


            WelcomeCard(

                userName =

                    if (
                        user.fullName.isNotBlank()
                    ) {

                        user.fullName

                    } else {

                        "User"

                    },

                greeting = getGreeting(),

                subtitle = getSubtitle()

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
                modifier = Modifier.height(24.dp)
            )


            if (allSkills.isNotEmpty()) {

                val featuredSkill =
                    allSkills.first()


                FeaturedSkillCard(

                    title = featuredSkill.title,

                    description =
                        featuredSkill.description,

                    onClick = {

                        navController.navigate(
                            "skillDetail/${featuredSkill.id}"
                        )

                    }

                )

            }


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            LearningProgressCard(

                favorites =
                    FavoritesManager
                        .favoriteSkills
                        .size,

                skills =
                    myPostedSkillsCount,

                rating = "4.8"

            )


            Spacer(
                modifier = Modifier.height(32.dp)
            )


            Text(

                text = "Popular Categories",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

        }


        item {

            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                items(
                    DummyData.categories
                ) { category ->


                    CategoryChip(

                        title = category.title,

                        selected =
                            selectedCategory ==
                                    category.title,

                        onClick = {

                            selectedCategory =

                                if (
                                    selectedCategory ==
                                    category.title
                                ) {

                                    "All"

                                } else {

                                    category.title

                                }

                        }

                    )

                }

            }

        }


        item {

            Spacer(
                modifier = Modifier.height(32.dp)
            )


            Text(

                text = "🔥 Trending Skills",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

        }


        if (isLoadingSkills) {

            item {

                Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),

                    contentAlignment = Alignment.Center

                ) {

                    CircularProgressIndicator()

                }

            }

        } else if (filteredSkills.isEmpty()) {

            item {

                Text(

                    text = "No skills found 😕",

                    style =
                        MaterialTheme.typography.bodyLarge

                )

            }

        } else {


            items(

                items = filteredSkills,

                key = { skill -> skill.id }

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


        item {

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            Text(

                text = "⭐ Top Mentors",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

        }


        item {

            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(16.dp)

            ) {

                items(
                    DummyData.mentors
                ) { mentor ->


                    MentorCard(

                        name = mentor.name,

                        skill = mentor.skill,

                        rating = mentor.rating

                    )

                }

            }

        }

    }

}