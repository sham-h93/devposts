package com.hshamkhani.navigation.routes

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hshamkhani.articles.NewsArticlesScreen
import com.hshamkhani.articles.route.NewsArticlesScreenRoute

fun NavGraphBuilder.newsArticlesNav(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Long) -> Unit,
) = composable<NewsArticlesScreenRoute> {
    NewsArticlesScreen(
        modifier = modifier,
        navigateToArticleDetailScreen = navigateToArticleDetailScreen,
    )
}
