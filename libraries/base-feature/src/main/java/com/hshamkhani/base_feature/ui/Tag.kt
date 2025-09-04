package com.hshamkhani.base_feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.design_system.theme.DevPostsTheme

@Composable
fun Tags(modifier: Modifier = Modifier, tags: List<String>) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { tag ->
            Tag(name = tag)
        }
    }
}

@Composable
private fun Tag(modifier: Modifier = Modifier, name: String) {
    Text(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface)
            .padding(4.dp),
        text = name,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Preview
@Composable
private fun TagPreview() {
    DevPostsTheme {
        Tag(
            name = "#tagName",
        )
    }
}
