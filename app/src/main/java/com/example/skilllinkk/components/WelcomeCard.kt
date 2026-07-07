package com.example.skilllinkk.components
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WelcomeCard(

    userName: String,

    greeting: String,

    subtitle: String

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(

                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary),

                contentAlignment = Alignment.Center

            ) {

                Icon(

                    imageVector = Icons.Default.Person,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.onPrimary,

                    modifier = Modifier.size(36.dp)

                )

            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(

                        text = greeting,

                        style = MaterialTheme.typography.titleMedium

                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(

                        imageVector = Icons.Default.WavingHand,

                        contentDescription = null,

                        tint = MaterialTheme.colorScheme.primary

                    )

                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(

                    text = userName,

                    style = MaterialTheme.typography.headlineSmall,

                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(

                    text = subtitle,

                    style = MaterialTheme.typography.bodyMedium

                )

            }

        }

    }

}