package com.formation.app.common.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color.MainGreen,
    background = Color.White,
    surface = Color.White,
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

@Composable
fun FormationTheme( content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}