package com.hshamkhani.articledetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.HotlineNewsTheme

@Composable
internal fun ErrorState(modifier: Modifier = Modifier, message: String) {
    val colors =
        CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.error,
        )
    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp),
        colors = colors,
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier =
                Modifier
                    .size(64.dp),
                imageVector = Icons.Rounded.Warning,
                contentDescription = "Error Icon",
            )

            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge,
                text = message,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorItemPreview() {
    HotlineNewsTheme {
        Surface {
            ErrorState(
                modifier = Modifier.fillMaxWidth(),
                message = "An error occurred while loading articles.",
            )
        }
    }
}
