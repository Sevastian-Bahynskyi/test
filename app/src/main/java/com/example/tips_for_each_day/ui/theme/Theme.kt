package com.example.tips_for_each_day.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xff99cc00),
    primaryVariant = LightGreen,
    secondary = Color(0xFF009933),
    background = Color(0xfff0fff0)

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TipsforeachdayTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colors = if (darkTheme)
    {
        DarkColorPalette
    } else
    {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}