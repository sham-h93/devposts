package com.hshamkhani.articledetails.mapper

import com.hshamkhani.articledetails.model.UiArticleDetail
import com.hshamkhani.articledetails.model.UiSource
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.domain.model.ArticleDetail
import com.hshamkhani.domain.model.Source

internal fun ArticleDetail.asUiArticleDetail(): UiArticleDetail =
    UiArticleDetail(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt.toReadableFormat(),
        source = source.asUiSource(),
        title = title,
        url = url,
        urlToImage = urlToImage,
    )

private fun Source.asUiSource(): UiSource =
    UiSource(
        id = id,
        name = name,
    )
