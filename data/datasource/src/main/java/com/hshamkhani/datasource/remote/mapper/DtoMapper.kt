package com.hshamkhani.datasource.remote.mapper

import com.hshamkhani.common.toTimeStampInMillis
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import com.hshamkhani.datasource.remote.model.ArticleDto
import com.hshamkhani.datasource.remote.model.OrganizationDto
import com.hshamkhani.datasource.remote.model.UserDto

internal fun ArticleDto.asArticleEntity(id: Int): ArticleEntity = ArticleEntity(
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
    organization = organization?.asOrganizationEntity(),
)

internal fun UserDto.asUserEntity(): UserEntity = UserEntity(
    name = name,
    githubUsername = githubUsername,
    twitterUsername = twitterUsername,
    websiteUrl = websiteUrl,
    profileImage = profileImage,
)

internal fun OrganizationDto.asOrganizationEntity(): OrganizationEntity = OrganizationEntity(
    name = name,
    profileImage = profileImage,
)
