package com.hshamkhani.news_list.mapper

import com.hshamkhani.domain.model.Article
import com.hshamkhani.news_list.model.UiArticle

fun Article.asUiArticle(): UiArticle =
    UiArticle(
        id = id,
        title = title,
        description = description,
        urlToImage = urlToImage,
    )
