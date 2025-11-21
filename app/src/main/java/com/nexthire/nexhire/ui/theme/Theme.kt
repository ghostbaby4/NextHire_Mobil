package com.nexthire.nexhire.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Blue800,
    secondary = Blue600,
    tertiary = Blue300,
    background = Blue100,
    surface = Blue100,
    onPrimary = TextLight,
    onSecondary = TextLight,
    onBackground = Blue900,
    onSurface = Blue900
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue300,
    secondary = Blue600,
    tertiary = Blue800,
    background = Blue900,
    surface = Blue800,
    onPrimary = TextPrimary,
    onSecondary = TextLight,
    onBackground = TextLight,
    onSurface = TextLight
)

@Composable
fun NexHireTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}