@file:OptIn(ExperimentalMaterial3Api::class)

package com.hshamkhani.news_list.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hshamkhani.designsystem.theme.HotlineNewsTheme
import com.hshamkhani.designsystem.ui.TopBarTitleText

@Composable
internal fun NewsArticlesTopBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    MediumTopAppBar(
        modifier = modifier,
        title = {
            TopBarTitleText(
                text = title,
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
private fun NewsArticlesTopBarPreview() {
    HotlineNewsTheme {
        Scaffold(
            topBar = {
                NewsArticlesTopBar(
                    title = "News Articles",
                )
            },
        ) { paddingValues -> }
    }
}
