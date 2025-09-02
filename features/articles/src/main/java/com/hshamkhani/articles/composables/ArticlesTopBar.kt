@file:OptIn(ExperimentalMaterial3Api::class)

package com.hshamkhani.articles.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hshamkhani.base_feature.theme.DevPostsTheme

@Composable
internal fun ArticlesTopBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val colors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.background,
    )
    MediumTopAppBar(
        modifier = modifier,
        colors = colors,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
private fun ArticlesTopBarPreview() {
    DevPostsTheme {
        Scaffold(
            topBar = {
                ArticlesTopBar(
                    title = "Articles",
                )
            },
        ) { _ -> }
    }
}
