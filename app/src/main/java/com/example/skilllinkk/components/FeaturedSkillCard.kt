package com.example.skilllinkk.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FeaturedSkillCard(

    title: String,

    description: String,

    onClick: () -> Unit

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.AutoAwesome,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary

                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(

                    text = "Featured Skill",

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold

                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(

                text = title,

                style = MaterialTheme.typography.headlineSmall,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = description,

                style = MaterialTheme.typography.bodyMedium

            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                onClick = onClick,

                modifier = Modifier.align(Alignment.End)

            ) {

                Text("Start Learning")

            }

        }

    }

}
