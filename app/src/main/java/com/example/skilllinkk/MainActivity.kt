package com.example.skilllinkk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.skilllinkk.navigation.AppNavigation
import com.example.skilllinkk.ui.theme.SkillLinkkTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SkillLinkkTheme {
                AppNavigation()
            }
        }
    }
}