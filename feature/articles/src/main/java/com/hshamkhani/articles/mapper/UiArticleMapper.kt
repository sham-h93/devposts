package com.hshamkhani.articles.mapper

import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.domain.model.Article

internal fun Article.asUiArticle(): UiArticle =
    UiArticle(
        id = id,
        title = title,
        description = description,
        urlToImage = urlToImage,
    )
