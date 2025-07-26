package com.hshamkhani.designsystem.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun backgroundGradient(color: Color) = Brush.verticalGradient(
    0f to Color.Transparent,
    .9f to color,
    startY = 20.8f,
)
