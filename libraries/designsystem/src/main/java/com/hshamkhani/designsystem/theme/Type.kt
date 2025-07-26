package com.hshamkhani.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography =
    Typography(
        displayMedium =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                lineHeight = 48.sp,
                letterSpacing = 0.5.sp,
            ),
        displaySmall =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.5.sp,
            ),
        titleLarge =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.5.sp,
            ),
        titleMedium =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.5.sp,
            ),
        titleSmall =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 30.sp,
                letterSpacing = 0.5.sp,
            ),
        bodyLarge =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 27.sp,
                letterSpacing = 0.5.sp,
            ),
        bodyMedium =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        labelLarge =
            TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 21.sp,
            ),
        labelMedium =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
        labelSmall =
            TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                lineHeight = 15.sp,
            ),
    )
