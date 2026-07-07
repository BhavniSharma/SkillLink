package com.example.skilllinkk.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.skilllinkk.FavoritesManager

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MainScreen(
    navController: NavController,
    initialTab: Int = 0
) {

    var selectedIndex by remember(initialTab) {
        mutableIntStateOf(initialTab)
    }


    LaunchedEffect(Unit) {

        FavoritesManager.loadFavorites()

    }


    val items = listOf(

        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home
        ),

        BottomNavItem(
            title = "Search",
            icon = Icons.Default.Search
        ),

        BottomNavItem(
            title = "Add",
            icon = Icons.Default.Add
        ),

        BottomNavItem(
            title = "Favorites",
            icon = Icons.Default.Favorite
        ),

        BottomNavItem(
            title = "Profile",
            icon = Icons.Default.Person
        )

    )


    Scaffold(

        bottomBar = {

            NavigationBar {

                items.forEachIndexed { index, item ->

                    NavigationBarItem(

                        selected = selectedIndex == index,

                        onClick = {

                            selectedIndex = index

                        },

                        icon = {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )

                        },

                        label = {

                            Text(
                                text = item.title
                            )

                        }

                    )

                }

            }

        }

    ) { padding ->

        when (selectedIndex) {

            0 -> {

                HomeScreen(
                    navController = navController,
                    padding = padding
                )

            }


            1 -> {

                SearchScreen(
                    navController = navController
                )

            }


            2 -> {

                AddSkillScreen(
                    navController = navController
                )

            }


            3 -> {

                FavoritesScreen(
                    navController = navController
                )

            }


            4 -> {

                ProfileScreen(
                    navController = navController
                )

            }

        }

    }

}