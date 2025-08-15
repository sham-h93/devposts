package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.AppTheme

@Composable
fun Reactions(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color = MaterialTheme.colorScheme.onBackground,
    count: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun LikesPreview() {
    AppTheme {
        Reactions(
            icon = Icons.Default.Favorite,
            iconTint = MaterialTheme.colorScheme.error,
            count = 13,
        )
    }
}
