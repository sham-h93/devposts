package com.hshamkhani.articles.mapper

import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.articles.model.UiOrganization
import com.hshamkhani.articles.model.UiUser
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User

internal fun Article.asUiArticle(): UiArticle = UiArticle(
    id = id,
    title = title,
    description = description,
    image = image,
    publishDate = publishDate,
    reactionsCount = reactionsCount,
    language = language,
    tags = tags,
    user = user.asUiUser(),
    organization = organization.asUiOrganization()

)

private fun User.asUiUser(): UiUser = UiUser(
    name = name,
    username = username,
    profileImage = profileImage
)

private fun Organization.asUiOrganization(): UiOrganization = UiOrganization(
    name = name, profileImage = profileImage
)
