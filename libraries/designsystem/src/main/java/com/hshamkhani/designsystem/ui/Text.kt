package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.HotlineNewsTheme

@Composable
fun TopBarTitleText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
fun NewsTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        modifier = modifier,
        style =
            MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
    )
}

@Composable
fun BodyText(
    modifier: Modifier = Modifier,
    bold: Boolean = false,
    text: String,
) {
    Text(
        text = text,
        modifier = modifier,
        style =
            MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (bold) FontWeight.Medium else FontWeight.Normal,
                color =
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = if (bold) 0.5f else 1f,
                    ),
            ),
    )
}

@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
    )
}

@Preview
@Composable
private fun Textpreview() {
    HotlineNewsTheme {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TopBarTitleText(
                text = "Top Bar Title Text",
                modifier = Modifier.fillMaxWidth(),
            )
            NewsTitle(
                text = "News Title",
                modifier = Modifier.fillMaxWidth(),
            )
            BodyText(
                text = LoremIpsum(20).values.first(),
                modifier = Modifier.fillMaxWidth(),
            )
            LabelText(
                text = "Label Text",
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
