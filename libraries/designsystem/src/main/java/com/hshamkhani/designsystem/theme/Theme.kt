package com.hshamkhani.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightColorScheme =
    lightColorScheme(
        primary = primary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        surfaceContainer = surfaceContainer,
        surfaceDim = dim,
        error = error,
        onError = onError,
    )

private val darkColorScheme =
    darkColorScheme(
        primary = primaryDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        surfaceContainer = surfaceContainerDark,
        surfaceDim = dimDark,
        error = error,
        onError = onError,
    )

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme =
        when {
            darkTheme -> darkColorScheme
            else -> lightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
