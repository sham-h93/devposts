package com.hshamkhani.articles.mapper

import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.common.and
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.common.withHashtag
import com.hshamkhani.domain.model.Article

internal fun Article.asUiArticle(): UiArticle = UiArticle(
    id = id,
    publishers = user.username and organization.username,
    articleTitle = title,
    tags = tags.withHashtag(),
    publishDate = publishDate.toReadableFormat(),
    image = image,
    reactionsCount = reactionsCount,
    language = language,
    userProfileImage = user.profileImage,
    orientationProfileImage = organization.profileImage,
)
