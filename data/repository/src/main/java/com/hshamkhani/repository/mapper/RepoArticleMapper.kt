package com.hshamkhani.repository.mapper

import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser

internal fun RepoArticle.asArticle(): Article = Article(
    id = id,
    title = title,
    description = description,
    image = image,
    publishDate = publishDate,
    url = url,
    commentsCount = commentsCount,
    reactionsCount = reactionsCount,
    readingMinutes = readingMinutes,
    language = language,
    tags = tags,
    user = user.asUser(),
    organization = organization.asOrganization()

)

private fun RepoUser.asUser(): User = User(
    name = name,
    username = username,
    githubUsername = githubUsername,
    twitterUsername = twitterUsername,
    websiteUrl = websiteUrl,
    profileImage = profileImage
)

private fun RepoOrganization.asOrganization(): Organization = Organization(
    name = name, username = username, profileImage = profileImage
)
