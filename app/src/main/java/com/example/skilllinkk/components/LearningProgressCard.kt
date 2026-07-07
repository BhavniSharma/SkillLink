package com.example.skilllinkk.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LearningProgressCard(

    favorites: Int,

    skills: Int,

    rating: String

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),

        elevation = CardDefaults.cardElevation(8.dp)

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.AutoGraph,

                    contentDescription = null

                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(

                    "Your Learning",

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold

                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("❤️ Favorites : $favorites")

            Spacer(modifier = Modifier.height(8.dp))

            Text("📚 Skills : $skills")

            Spacer(modifier = Modifier.height(8.dp))

            Text("⭐ Average Rating : $rating")

            Spacer(modifier = Modifier.height(18.dp))

            Text(

                "Keep Learning! 🚀",

                style = MaterialTheme.typography.bodyLarge,

                fontWeight = FontWeight.SemiBold

            )

        }

    }

}