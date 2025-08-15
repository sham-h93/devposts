package com.hshamkhani.designsystem.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hshamkhani.designsystem.theme.AppTheme

@Composable
fun AnimatedSurface(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition("animated_surface")

    val animatedColor by transition.animateColor(
        initialValue = MaterialTheme.colorScheme.surface,
        targetValue = MaterialTheme.colorScheme.surface.copy(alpha = .2f),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1_000),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(modifier = modifier.background(animatedColor))
}

@Preview
@Composable
private fun AnimatedSurfacePreview() {
    AppTheme {
        AnimatedSurface(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
