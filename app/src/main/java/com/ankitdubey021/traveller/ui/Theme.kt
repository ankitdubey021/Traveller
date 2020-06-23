package com.ankitdubey021.traveller.ui

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val LightThemeColors = lightColorPalette(
        primary = Pink,
        primaryVariant = Red900,
        onPrimary = Color.White,
        secondary = Red700,
        secondaryVariant = Red900,
        onSecondary = Color.White,
        error = Red800
)

private val DarkThemeColors = darkColorPalette(
        primary = Red300,
        primaryVariant = Red700,
        onPrimary = Color.Black,
        secondary = Red300,
        onSecondary = Color.White,
        error = Red200
)
@Composable
fun MovieComposerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}