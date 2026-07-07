package com.example.skilllinkk.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SkillCard(

    title: String,

    subtitle: String,

    mentor: String,

    learners: String,

    rating: String,

    onClick: () -> Unit

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(

                    text = title,

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.weight(1f)

                )

                Surface(

                    color = MaterialTheme.colorScheme.primaryContainer,

                    shape = RoundedCornerShape(50)

                ) {

                    Row(

                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(

                            Icons.Default.Star,

                            contentDescription = null,

                            tint = MaterialTheme.colorScheme.primary,

                            modifier = Modifier.size(18.dp)

                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(

                            text = rating,

                            fontWeight = FontWeight.Bold

                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(

                text = subtitle,

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.Person,

                    contentDescription = null,

                    modifier = Modifier.size(18.dp),

                    tint = MaterialTheme.colorScheme.primary

                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(

                    text = mentor,

                    fontWeight = FontWeight.Medium

                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = learners,

                style = MaterialTheme.typography.bodySmall,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.End,

                verticalAlignment = Alignment.CenterVertically

            ) {

                TextButton(

                    onClick = onClick

                ) {

                    Text(

                        "View Details"

                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(

                        Icons.Default.ArrowForward,

                        contentDescription = null,

                        modifier = Modifier.size(18.dp)

                    )

                }

            }

        }

    }

}