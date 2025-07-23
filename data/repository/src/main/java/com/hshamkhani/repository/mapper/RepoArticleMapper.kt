package com.hshamkhani.repository.mapper

import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.ArticleDetail
import com.hshamkhani.domain.model.Source
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoSource

internal fun RepoArticle.asArticle(): Article =
    Article(
        id = id,
        title = title,
        description = description,
        urlToImage = urlToImage,
    )

internal fun RepoSource.asSource(): Source =
    Source(
        id = id,
        name = name,
    )

internal fun RepoArticle.asArticleDetail(): ArticleDetail =
    ArticleDetail(
        id = id,
        title = title,
        description = description,
        urlToImage = urlToImage,
        author = author,
        content = content,
        publishedAt = publishedAt,
        url = url,
        source = Source.asSource(),
    )
