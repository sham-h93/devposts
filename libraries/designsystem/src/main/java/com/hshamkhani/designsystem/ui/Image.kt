package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun Image(modifier: Modifier = Modifier, imageUri: String) {
    AsyncImage(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceDim.copy(alpha = .1f)),
        model = imageUri,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}
