package com.hshamkhani.datasource.mapper

import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.SourceEntity
import com.hshamkhani.datasource.remote.model.ArticleDto
import com.hshamkhani.datasource.remote.model.SourceDto
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoSource
import java.util.UUID

internal fun ArticleDto.asArticleEntity(): ArticleEntity =
    ArticleEntity(
        id = UUID.randomUUID().hashCode(),
        title = title,
        description = description,
        author = author,
        content = content,
        publishedAt = publishedAt,
        source = source.asSourceEntity(),
        url = url,
        urlToImage = urlToImage,
    )

internal fun SourceDto.asSourceEntity(): SourceEntity =
    SourceEntity(
        id = id,
        name = name,
    )

internal fun ArticleEntity.asRepoArticle(): RepoArticle =
    RepoArticle(
        id = id,
        title = title,
        description = description,
        urlToImage = urlToImage,
        author = author,
        content = content,
        publishedAt = publishedAt,
        url = url,
        source = source.asRepoSource(),
    )

internal fun SourceEntity.asRepoSource(): RepoSource =
    RepoSource(
        id = id,
        name = name,
    )
