@file:OptIn(ExperimentalMaterial3Api::class)

package com.hshamkhani.articles.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hshamkhani.articles.R
import com.hshamkhani.designsystem.theme.DevPostsTheme

@Composable
internal fun NewsArticlesScreenScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NewsArticlesTopBar(
                title = stringResource(R.string.title_articles_screen),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        content(paddingValues)
    }
}

@Preview
@Composable
private fun NewsArticlesScreenScaffoldpreview() {
    DevPostsTheme {
        NewsArticlesScreenScaffold(
            content = {
                // Content goes here
            },
        )
    }
}
