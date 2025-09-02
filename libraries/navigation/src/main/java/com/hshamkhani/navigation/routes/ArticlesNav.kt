package com.hshamkhani.navigation.routes

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hshamkhani.articles.ArticlesScreen
import com.hshamkhani.articles.route.ArticlesScreenRoute

internal fun NavGraphBuilder.articlesNav(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Int) -> Unit,
) = composable<ArticlesScreenRoute> {
    ArticlesScreen(
        modifier = modifier,
        navigateToArticleDetailScreen = navigateToArticleDetailScreen,
    )
}
