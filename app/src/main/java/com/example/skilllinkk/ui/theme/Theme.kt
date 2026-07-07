package com.example.skilllinkk.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = RoyalBlue,
    secondary = SkyBlue,
    background = NavyBlue,
    surface = NavyBlue,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    secondary = RoyalBlue,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onBackground = DarkText,
    onSurface = DarkText
)

@Composable
fun SkillLinkkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}