package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.hshamkhani.designsystem.R

@Composable
fun ArticleImage(
    modifier: Modifier = Modifier,
    imageUri: String,
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    val painter = rememberAsyncImagePainter(model = imageUri)
    val state by painter.state.collectAsState()

    when (state) {
        is AsyncImagePainter.State.Empty -> {
            Box(modifier = modifier.background(color = MaterialTheme.colorScheme.surface))
        }
        is AsyncImagePainter.State.Loading -> {
            AnimatedSurface(modifier = modifier)
        }
        is AsyncImagePainter.State.Success -> {
            Image(
                modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
                painter = painter,
                contentScale = contentScale,
                contentDescription = null,
            )
        }
        is AsyncImagePainter.State.Error -> {
            ErrorSection(
                modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
                message = stringResource(R.string.async_image_error_loading_image),
            )
        }
    }
}
