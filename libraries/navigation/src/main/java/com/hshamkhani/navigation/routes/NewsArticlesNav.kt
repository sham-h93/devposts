package com.hshamkhani.navigation.routes

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hshamkhani.articledetails.route.ArticleDetailsScreenRoute
import com.hshamkhani.articles.NewsArticlesScreen

fun NavGraphBuilder.newsArticlesNav(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Int) -> Unit,
) = composable<ArticleDetailsScreenRoute>(
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
