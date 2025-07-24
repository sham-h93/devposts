@file:OptIn(ExperimentalMaterial3Api::class)

package com.hshamkhani.news_list.composables

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
import com.hshamkhani.designsystem.theme.HotlineNewsTheme
import com.hshamkhani.news_list.R

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
    HotlineNewsTheme {
        NewsArticlesScreenScaffold {
            // Content goes here
        }
    }
}
