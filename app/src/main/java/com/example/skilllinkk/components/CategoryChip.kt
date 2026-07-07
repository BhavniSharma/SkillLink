package com.example.skilllinkk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(

    title: String,

    selected: Boolean,

    onClick: () -> Unit

) {

    Text(

        text = title,

        modifier = Modifier
            .background(
                color =
                    if (selected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant,

                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color =
                    if (selected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),

                shape = RoundedCornerShape(50.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 18.dp, vertical = 10.dp),

        color =
            if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSurface,

        fontWeight = FontWeight.SemiBold

    )

}