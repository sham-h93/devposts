package com.hshamkhani.navigation.routes

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.hshamkhani.articledetails.ArticleDetailsScreen
import com.hshamkhani.articledetails.route.ArticleDetailsScreenRoute

internal fun NavGraphBuilder.articleDetailsNav(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
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
    ArticleDetailsScreen(
        modifier = modifier,
        navigateUp = navigateUp,
    )
}

fun NavHostController.navigateToArticleDetailsScreen(
    articleId: Int,
    navOptions: NavOptionsBuilder.() -> Unit,
) {
    navigate(
        ArticleDetailsScreenRoute(
            articleId = articleId,
        ),
    ) {
        navOptions()
    }
}
