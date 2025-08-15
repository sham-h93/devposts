package com.hshamkhani.articles.mapper

import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.common.withHashtag
import com.hshamkhani.common.withOrWithout
import com.hshamkhani.domain.model.Article

internal fun Article.asUiArticle(): UiArticle = UiArticle(
    id = id,
    publishers = user.name withOrWithout organization?.name,
    articleTitle = title,
    tags = tags.withHashtag(),
    publishDate = publishDate.toReadableFormat(),
    image = image,
    reactionsCount = reactionsCount,
    language = language,
    userProfileImage = user.profileImage,
    organizationProfileImage = organization?.profileImage,
)
