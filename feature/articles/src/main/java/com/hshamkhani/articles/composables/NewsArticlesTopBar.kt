@file:OptIn(ExperimentalMaterial3Api::class)

package com.hshamkhani.articles.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hshamkhani.designsystem.theme.AppTheme

@Composable
internal fun NewsArticlesTopBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    MediumTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
private fun NewsArticlesTopBarPreview() {
    AppTheme {
        Scaffold(
            topBar = {
                NewsArticlesTopBar(
                    title = "News Articles",
                )
            },
        ) { paddingValues -> }
    }
}
