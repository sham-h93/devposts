package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.R

@Composable
fun ErrorSection(modifier: Modifier = Modifier, message: String?, onRetry: (() -> Unit)? = null) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp),
            imageVector = Icons.Rounded.Warning,
            tint = MaterialTheme.colorScheme.error,
            contentDescription = "Error Icon",
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            text = message ?: stringResource(R.string.error_loading_articles),
        )

        if (onRetry != null) {
            OutlinedButton(
                onClick = onRetry,
            ) {
                Text(
                    text = stringResource(R.string.error_item_retry_button),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
