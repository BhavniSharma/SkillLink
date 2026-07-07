package com.example.skilllinkk.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(

    query: String,

    onQueryChange: (String) -> Unit

) {

    OutlinedTextField(

        value = query,

        onValueChange = onQueryChange,

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

        singleLine = true,

        shape = RoundedCornerShape(20.dp),

        placeholder = {

            Text("Search skills or mentors...")

        },

        leadingIcon = {

            Icon(

                imageVector = Icons.Default.Search,

                contentDescription = null

            )

        },

        trailingIcon = {

            IconButton(

                onClick = { }

            ) {

                Icon(

                    imageVector = Icons.Default.Tune,

                    contentDescription = "Filter"

                )

            }

        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = MaterialTheme.colorScheme.primary,

            focusedLabelColor = MaterialTheme.colorScheme.primary,

            cursorColor = MaterialTheme.colorScheme.primary,

            unfocusedBorderColor = MaterialTheme.colorScheme.outline

        )

    )

}