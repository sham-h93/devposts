package com.hshamkhani.articledetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.DevPostsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun ArticleDetailsScreenScaffold(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    content: @Composable ColumnScope.(PaddingValues) -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (scrollState.canScrollBackward) {
                ReturnPageStart(
                    onClick = {
                        scope.launch {
                            scrollState.animateScrollTo(value = 0)
                        }
                    },
                )
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                content(paddingValues)
            }
        }
    }
}

@Preview
@Composable
private fun ArticleDetailsScreenScaffoldPreview() {
    DevPostsTheme {
        ArticleDetailsScreenScaffold(
            scope = rememberCoroutineScope(),
        ) {
            // screen content goes here
        }
    }
}

@Composable
private fun ReturnPageStart(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null,
            )
        },
    )
}
