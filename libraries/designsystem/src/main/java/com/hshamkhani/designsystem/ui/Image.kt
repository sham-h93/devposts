package com.hshamkhani.designsystem.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun Image(modifier: Modifier = Modifier, imageUri: String) {
    AsyncImage(
        modifier = modifier,
        model = imageUri,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}
