package com.hshamkhani.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hshamkhani.articles.route.NewsArticlesScreenRoute
import com.hshamkhani.navigation.routes.articleDetailsNav
import com.hshamkhani.navigation.routes.navigateToArticleDetailsScreen
import com.hshamkhani.navigation.routes.newsArticlesNav

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = NewsArticlesScreenRoute,
    ) {
        newsArticlesNav(
            modifier = modifier,
            navigateToArticleDetailScreen = {
                navHostController.navigateToArticleDetailsScreen(
                    navOptions = {
                        popUpTo(NewsArticlesScreenRoute) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    },
                )
            },
        )
        articleDetailsNav(
            modifier = modifier,
            navigateUp = {
                navHostController.navigateUp()
            },
        )
    }
}
