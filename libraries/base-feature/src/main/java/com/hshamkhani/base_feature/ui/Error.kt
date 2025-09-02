package com.hshamkhani.base_feature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.hshamkhani.base_feature.R
import com.hshamkhani.base_feature.theme.DevPostsTheme

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
                .fillMaxWidth(.2f)
                .aspectRatio(1f),
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

@Preview(showBackground = true)
@Composable
private fun ErrorSectionPreview() {
    DevPostsTheme {
        ErrorSection(
            modifier = Modifier.fillMaxSize(),
            message = LoremIpsum(10).values.first(),
        )
    }
}
