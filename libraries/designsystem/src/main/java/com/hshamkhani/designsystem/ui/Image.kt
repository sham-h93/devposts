package com.hshamkhani.designsystem.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.hshamkhani.designsystem.R

@Composable
fun Image(
    modifier: Modifier = Modifier,
    imageUri: String,
) {
    AsyncImage(
        modifier =
        modifier,
        model = imageUri,
        placeholder = painterResource(id = R.drawable.loading_placeholder_image),
        error = painterResource(id = R.drawable.placeholder_image),
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
    )
}
