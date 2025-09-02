package com.hshamkhani.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hshamkhani.articles.route.ArticlesScreenRoute
import com.hshamkhani.navigation.routes.articleDetailsNav
import com.hshamkhani.navigation.routes.articlesNav
import com.hshamkhani.navigation.routes.navigateToArticleDetailsScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ArticlesScreenRoute,
    ) {
        articlesNav(
            modifier = modifier,
            navigateToArticleDetailScreen = { id ->
                navHostController.navigateToArticleDetailsScreen(
                    articleId = id,
                    navOptions = {
                        popUpTo(ArticlesScreenRoute) {
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
