package com.hshamkhani.navigation.routes

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hshamkhani.articles.NewsArticlesScreen
import com.hshamkhani.articles.route.NewsArticlesScreenRoute

fun NavGraphBuilder.newsArticlesNav(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Int) -> Unit,
) = composable<NewsArticlesScreenRoute>(
    enterTransition = {
        slideIntoContainer(
            SlideDirection.Left,
            animationSpec = tween(500),
        )
    },
    exitTransition = {
        slideOutOfContainer(
            SlideDirection.Right,
            animationSpec = tween(500),
        )
    },
) {
    NewsArticlesScreen(
        modifier = modifier,
        navigateToArticleDetailScreen = navigateToArticleDetailScreen,
    )
}
