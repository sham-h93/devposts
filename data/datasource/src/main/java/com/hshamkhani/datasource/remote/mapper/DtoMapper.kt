package com.hshamkhani.datasource.remote.mapper

import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser

internal fun ArticleEntity.asRepoArticle(): RepoArticle = RepoArticle(
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
    user = user.asRepoUser(),
    organization = organization?.asRepoOrganization(),
)

private fun UserEntity.asRepoUser(): RepoUser = RepoUser(
    name = name,
    githubUsername = githubUsername,
    twitterUsername = twitterUsername,
    websiteUrl = websiteUrl,
    profileImage = profileImage,

)

private fun OrganizationEntity.asRepoOrganization(): RepoOrganization = RepoOrganization(
    name = name,
    profileImage = profileImage,
)
