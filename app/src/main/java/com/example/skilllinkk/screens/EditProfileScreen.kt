package com.example.skilllinkk.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.firebase.StorageManager
import com.example.skilllinkk.model.User
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var fullName by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var bio by remember {
        mutableStateOf("")
    }

    var role by remember {
        mutableStateOf("Learner")
    }

    var imageUrl by remember {
        mutableStateOf("")
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isSaving by remember {
        mutableStateOf(false)
    }


    fun navigateToProfile() {

        navController.popBackStack()

    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        if (uri != null) {

            imageUri = uri

        }

    }


    LaunchedEffect(Unit) {

        val uid =
            FirebaseAuthManager.auth.currentUser?.uid

        if (uid == null) {

            isLoading = false

            Toast.makeText(
                context,
                "User not logged in",
                Toast.LENGTH_SHORT
            ).show()

            return@LaunchedEffect

        }


        FirestoreManager.db
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                val user =
                    document.toObject(User::class.java)

                if (user != null) {

                    fullName = user.fullName

                    phone = user.phone

                    bio = user.bio

                    role = user.role

                    imageUrl = user.imageUrl

                }

                isLoading = false

            }
            .addOnFailureListener { exception ->

                isLoading = false

                Toast.makeText(
                    context,
                    exception.message
                        ?: "Failed to load profile",
                    Toast.LENGTH_LONG
                ).show()

            }

    }


    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Edit Profile"
                    )

                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navigateToProfile()

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


        if (isLoading) {

            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentAlignment = Alignment.Center

            ) {

                CircularProgressIndicator()

            }

        } else {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
                    .verticalScroll(
                        rememberScrollState()
                    ),

                horizontalAlignment =
                    Alignment.CenterHorizontally

            ) {


                Surface(

                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),

                    shape = CircleShape,

                    color =
                        MaterialTheme.colorScheme.primary

                ) {

                    when {

                        imageUri != null -> {

                            AsyncImage(

                                model = imageUri,

                                contentDescription =
                                    "Selected profile photo",

                                modifier =
                                    Modifier.fillMaxSize()

                            )

                        }


                        imageUrl.isNotBlank() -> {

                            AsyncImage(

                                model = imageUrl,

                                contentDescription =
                                    "Profile photo",

                                modifier =
                                    Modifier.fillMaxSize()

                            )

                        }


                        else -> {

                            Box(

                                modifier =
                                    Modifier.fillMaxSize(),

                                contentAlignment =
                                    Alignment.Center

                            ) {

                                Icon(

                                    imageVector =
                                        Icons.Default.Person,

                                    contentDescription = null,

                                    tint = Color.White,

                                    modifier =
                                        Modifier.size(60.dp)

                                )

                            }

                        }

                    }

                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                TextButton(

                    enabled = !isSaving,

                    onClick = {

                        launcher.launch("image/*")

                    }

                ) {

                    Text(
                        text = "Change Photo"
                    )

                }


                Spacer(
                    modifier = Modifier.height(24.dp)
                )


                OutlinedTextField(

                    value = fullName,

                    onValueChange = {

                        fullName = it

                    },

                    label = {

                        Text(
                            text = "Full Name"
                        )

                    },

                    enabled = !isSaving,

                    singleLine = true,

                    modifier =
                        Modifier.fillMaxWidth()

                )


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                OutlinedTextField(

                    value = phone,

                    onValueChange = {

                        phone = it

                    },

                    label = {

                        Text(
                            text = "Phone Number"
                        )

                    },

                    enabled = !isSaving,

                    singleLine = true,

                    modifier =
                        Modifier.fillMaxWidth()

                )


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                OutlinedTextField(

                    value = bio,

                    onValueChange = {

                        bio = it

                    },

                    label = {

                        Text(
                            text = "Bio"
                        )

                    },

                    enabled = !isSaving,

                    modifier =
                        Modifier.fillMaxWidth(),

                    minLines = 3

                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Text(

                    text = "Role",

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold

                )


                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {


                    RadioButton(

                        selected =
                            role == "Learner",

                        enabled = !isSaving,

                        onClick = {

                            role = "Learner"

                        }

                    )


                    Text(
                        text = "Learner"
                    )


                    Spacer(
                        modifier = Modifier.width(20.dp)
                    )


                    RadioButton(

                        selected =
                            role == "Mentor",

                        enabled = !isSaving,

                        onClick = {

                            role = "Mentor"

                        }

                    )


                    Text(
                        text = "Mentor"
                    )

                }


                Spacer(
                    modifier = Modifier.height(30.dp)
                )


                Button(

                    modifier =
                        Modifier.fillMaxWidth(),

                    enabled = !isSaving,

                    onClick = {


                        if (fullName.isBlank()) {

                            Toast.makeText(
                                context,
                                "Full name cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button

                        }


                        val firebaseUser =
                            FirebaseAuthManager
                                .auth
                                .currentUser


                        if (firebaseUser == null) {

                            Toast.makeText(
                                context,
                                "User not logged in",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button

                        }


                        val uid =
                            firebaseUser.uid

                        val email =
                            firebaseUser.email ?: ""


                        isSaving = true


                        fun saveUser(
                            finalImageUrl: String
                        ) {

                            val updatedUser = User(

                                uid = uid,

                                fullName =
                                    fullName.trim(),

                                email = email,

                                phone =
                                    phone.trim(),

                                bio =
                                    bio.trim(),

                                role = role,

                                imageUrl =
                                    finalImageUrl

                            )


                            FirestoreManager.db
                                .collection("users")
                                .document(uid)
                                .set(updatedUser)
                                .addOnSuccessListener {

                                    isSaving = false

                                    Toast.makeText(
                                        context,
                                        "Profile Updated Successfully 🎉",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    navigateToProfile()

                                }
                                .addOnFailureListener { exception ->

                                    isSaving = false

                                    Toast.makeText(
                                        context,
                                        exception.message
                                            ?: "Failed to update profile",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                        }


                        val selectedImage =
                            imageUri


                        if (selectedImage != null) {

                            val imageRef =
                                StorageManager
                                    .storage
                                    .reference
                                    .child("profile_images")
                                    .child(
                                        "${UUID.randomUUID()}.jpg"
                                    )


                            imageRef
                                .putFile(selectedImage)
                                .continueWithTask { task ->

                                    if (!task.isSuccessful) {

                                        throw task.exception
                                            ?: Exception(
                                                "Image upload failed"
                                            )

                                    }

                                    imageRef.downloadUrl

                                }
                                .addOnSuccessListener { uri ->

                                    saveUser(
                                        uri.toString()
                                    )

                                }
                                .addOnFailureListener { exception ->

                                    isSaving = false

                                    Toast.makeText(
                                        context,
                                        exception.message
                                            ?: "Image Upload Failed",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                        } else {

                            saveUser(imageUrl)

                        }

                    }

                ) {


                    if (isSaving) {

                        CircularProgressIndicator(

                            modifier =
                                Modifier.size(22.dp),

                            strokeWidth = 2.dp

                        )

                    } else {

                        Text(
                            text = "Save Changes"
                        )

                    }

                }


                Spacer(
                    modifier = Modifier.height(30.dp)
                )

            }

        }

    }

}