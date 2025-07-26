package com.hshamkhani.articledetails.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hshamkhani.designsystem.theme.HotlineNewsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ArticleDetailsScreenScaffold(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    navigateUp: () -> Unit,
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
            ReturnIcon(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(8.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color.LightGray.copy(alpha = .5f)),
                onClick = navigateUp,
            )
        }
    }
}

@Preview
@Composable
private fun ArticleDetailsScreenScaffoldPreview() {
    HotlineNewsTheme {
        ArticleDetailsScreenScaffold(
            scope = rememberCoroutineScope(),
            navigateUp = {},
        ) {
            // screen content goes here
        }
    }
}

@Composable
private fun ReturnIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
            )
        },
    )
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
