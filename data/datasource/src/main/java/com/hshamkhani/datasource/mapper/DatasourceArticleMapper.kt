package com.hshamkhani.datasource.mapper

import com.hshamkhani.common.toTimeStampInMillis
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import com.hshamkhani.datasource.remote.model.ArticleDto
import com.hshamkhani.datasource.remote.model.OrganizationDto
import com.hshamkhani.datasource.remote.model.UserDto
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser

internal fun ArticleDto.Success.asArticleEntity(id: Int): ArticleEntity = ArticleEntity(
    id = id,
    title = title,
    description = description,
    image = image,
    publishDate = publishDate.toTimeStampInMillis(),
    url = url,
    commentsCount = commentsCount,
    reactionsCount = reactionsCount,
    readingMinutes = readingMinutes,
    language = language,
    tags = tags,
    user = user.asUserEntity(),
    organization = organization.asOrganizationEntity(),
)

private fun UserDto.asUserEntity(): UserEntity = UserEntity(
    name = name,
    username = username,
    githubUsername = githubUsername.orEmpty(),
    twitterUsername = twitterUsername.orEmpty(),
    websiteUrl = websiteUrl.orEmpty(),
    profileImage = profileImage,
)

private fun OrganizationDto.asOrganizationEntity(): OrganizationEntity = OrganizationEntity(
    name = name,
    username = username,
    profileImage = profileImage,
)
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
    organization = organization.asRepoOrganization(),
)

private fun UserEntity.asRepoUser(): RepoUser = RepoUser(
    name = name,
    username = username,
    githubUsername = githubUsername,
    twitterUsername = twitterUsername,
    websiteUrl = websiteUrl,
    profileImage = profileImage,
)

private fun OrganizationEntity.asRepoOrganization(): RepoOrganization = RepoOrganization(
    name = name,
    username = username,
    profileImage = profileImage,
)
