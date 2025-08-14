package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.AppTheme

@Composable
fun Likes(modifier: Modifier = Modifier, count: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun LikesPreview() {
    AppTheme {
        Likes(count = 13)
    }
}
