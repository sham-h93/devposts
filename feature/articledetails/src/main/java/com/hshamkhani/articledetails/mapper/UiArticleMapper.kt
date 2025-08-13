package com.hshamkhani.articledetails.mapper

import com.hshamkhani.articledetails.model.UiArticle
import com.hshamkhani.articledetails.model.UiOrganization
import com.hshamkhani.articledetails.model.UiUser
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User

internal fun Article.asUiArticle(): UiArticle = UiArticle(
    id = id,
    title = title,
    description = description,
    image = image,
    publishDate = publishDate.toReadableFormat(),
    url = url,
    commentsCount = commentsCount,
    reactionsCount = reactionsCount,
    readingMinutes = readingMinutes,
    language = language,
    tags = tags,
    user = user.asUiUser(),
    organization = organization.asUiOrganization(),

)

private fun User.asUiUser(): UiUser = UiUser(
    name = name,
    username = username,
    githubUsername = githubUsername,
    twitterUsername = twitterUsername,
    websiteUrl = websiteUrl,
    profileImage = profileImage,
)

private fun Organization.asUiOrganization(): UiOrganization = UiOrganization(
    name = name,
    username = username,
    profileImage = profileImage,
)
