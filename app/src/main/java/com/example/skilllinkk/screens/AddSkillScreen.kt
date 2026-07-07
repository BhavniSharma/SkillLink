package com.example.skilllinkk.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.firebase.SkillRepository
import com.example.skilllinkk.model.Skill
import com.example.skilllinkk.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSkillScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val categories = listOf(
        "Android",
        "Web Development",
        "Python",
        "Java",
        "Machine Learning",
        "Cloud",
        "Cyber Security",
        "Data Structures",
        "UI/UX",
        "Other"
    )

    val difficulties = listOf(
        "Beginner",
        "Intermediate",
        "Advanced"
    )

    var selectedCategory by remember {
        mutableStateOf(categories[0])
    }

    var selectedDifficulty by remember {
        mutableStateOf(difficulties[0])
    }

    var categoryExpanded by remember {
        mutableStateOf(false)
    }

    var difficultyExpanded by remember {
        mutableStateOf(false)
    }

    var isSaving by remember {
        mutableStateOf(false)
    }

    var currentUser by remember {
        mutableStateOf(User())
    }

    LaunchedEffect(Unit) {

        val uid = FirebaseAuthManager.auth.currentUser?.uid

        if (uid != null) {

            FirestoreManager.db
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener {

                    it.toObject(User::class.java)?.let { user ->

                        currentUser = user

                    }

                }

        }

    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Post a Skill")

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())

        ) {

            Text(

                text = "Share your knowledge with the community 📚",

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.SemiBold

            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(

                value = title,

                onValueChange = {

                    title = it

                },

                label = {

                    Text("Skill Title")

                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = description,

                onValueChange = {

                    description = it

                },

                label = {

                    Text("Description")

                },

                modifier = Modifier.fillMaxWidth(),

                minLines = 4

            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(

                expanded = categoryExpanded,

                onExpandedChange = {

                    categoryExpanded = !categoryExpanded

                }

            ) {

                OutlinedTextField(

                    value = selectedCategory,

                    onValueChange = {},

                    readOnly = true,

                    label = {

                        Text("Category")

                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = categoryExpanded
                        )

                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()

                )

                ExposedDropdownMenu(

                    expanded = categoryExpanded,

                    onDismissRequest = {

                        categoryExpanded = false

                    }

                ) {

                    categories.forEach {

                        DropdownMenuItem(

                            text = {

                                Text(it)

                            },

                            onClick = {

                                selectedCategory = it
                                categoryExpanded = false

                            }

                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(

                expanded = difficultyExpanded,

                onExpandedChange = {

                    difficultyExpanded = !difficultyExpanded

                }

            ) {

                OutlinedTextField(

                    value = selectedDifficulty,

                    onValueChange = {},

                    readOnly = true,

                    label = {

                        Text("Difficulty")

                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = difficultyExpanded
                        )

                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()

                )

                ExposedDropdownMenu(

                    expanded = difficultyExpanded,

                    onDismissRequest = {

                        difficultyExpanded = false

                    }

                ) {

                    difficulties.forEach {

                        DropdownMenuItem(

                            text = {

                                Text(it)

                            },

                            onClick = {

                                selectedDifficulty = it
                                difficultyExpanded = false

                            }

                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(32.dp))


            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    if (title.isBlank() || description.isBlank()) {

                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@Button

                    }

                    isSaving = true

                    val skill = Skill(

                        title = title,

                        description = description,

                        category = selectedCategory,

                        mentor = currentUser.fullName,

                        mentorEmail = currentUser.email,

                        learners = 0,

                        rating = 0.0,

                        createdBy = currentUser.uid,

                        createdAt = System.currentTimeMillis()

                    )

                    SkillRepository.addSkill(

                        skill = skill,

                        onSuccess = {

                            isSaving = false

                            Toast.makeText(
                                context,
                                "Skill Published Successfully 🎉",
                                Toast.LENGTH_SHORT
                            ).show()

                            title = ""
                            description = ""
                            selectedCategory = categories[0]
                            selectedDifficulty = difficulties[0]

                            navController.popBackStack()

                        },

                        onFailure = { e ->

                            isSaving = false

                            Toast.makeText(
                                context,
                                e.message ?: "Failed to publish skill",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    )

                }

            ) {

                if (isSaving) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )

                } else {

                    Text("Publish Skill")

                }

            }

        }

    }

}
