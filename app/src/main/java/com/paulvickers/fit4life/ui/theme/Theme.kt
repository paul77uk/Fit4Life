package com.paulvickers.fit4life.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = F4LLightOrange,
    primaryVariant = F4LBlack,
    secondary = F4LLightOrange,
    background = F4LBlack,
    onBackground = F4LLightGrey,
    onPrimary = F4LBlack,
    onSecondary = F4LLightGrey,
    surface = F4LDarkGrey,
    onSurface = F4LLightGrey
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = F4LLightOrange,
//    primary = F4LPurpleGrey,
    primaryVariant = F4LBlack,
    secondary= F4LLightOrange,
//    primary = F4LPurpleGrey,,
//    secondary = F4LPurpleGrey,
    background = F4LBlack,
    onBackground = F4LLightGrey,
    onPrimary = F4LBlack,
    onSecondary = F4LLightGrey,
    surface = F4LDarkGrey,
    onSurface = F4LLightGrey

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Fit4LifeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}