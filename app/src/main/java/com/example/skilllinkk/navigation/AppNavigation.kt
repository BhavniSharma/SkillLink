package com.example.skilllinkk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skilllinkk.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {

            SplashScreen(
                navController = navController
            )

        }


        composable(Routes.WELCOME) {

            WelcomeScreen(
                navController = navController
            )

        }


        composable(Routes.LOGIN) {

            LoginScreen(
                navController = navController
            )

        }


        composable(Routes.REGISTER) {

            RegisterScreen(
                navController = navController
            )

        }


        composable(Routes.FORGOT_PASSWORD) {

            ForgotPasswordScreen(
                navController = navController
            )

        }


        composable(Routes.HOME) {

            MainScreen(
                navController = navController
            )

        }


        composable(Routes.EDIT_PROFILE) {

            EditProfileScreen(
                navController = navController
            )

        }


        composable(Routes.SETTINGS) {

            SettingsScreen(
                navController = navController
            )

        }


        composable(

            route = Routes.SKILL_DETAIL,

            arguments = listOf(

                navArgument("skillId") {

                    type = NavType.StringType

                }

            )

        ) { backStackEntry ->

            val skillId =

                backStackEntry.arguments
                    ?.getString("skillId")
                    ?: ""


            SkillDetailScreen(

                navController = navController,

                skillId = skillId

            )

        }

    }

}