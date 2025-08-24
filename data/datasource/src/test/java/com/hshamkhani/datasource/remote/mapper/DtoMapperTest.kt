package com.hshamkhani.datasource.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import com.hshamkhani.datasource.remote.model.ArticleDto
import com.hshamkhani.datasource.remote.model.OrganizationDto
import com.hshamkhani.datasource.remote.model.UserDto
import java.time.ZonedDateTime
import org.junit.Before
import org.junit.Test

internal class DtoMapperTest {
    private lateinit var now: ZonedDateTime

    @Before
    fun setUp() {
        now = ZonedDateTime.now()
    }

    @Test
    fun asOrganizationEntity_normalDtoOrganization_returnsOrganizationEntity() {
        // GIVEN
        val organizationDto = fakeOrganizationDto()
        val expectedOrganization = OrganizationEntity(
            name = organizationDto.name,
            profileImage = organizationDto.profileImage,
        )

        // WHEN
        val mappedOrganization = organizationDto.asOrganizationEntity()

        // THEN
        assertThat(mappedOrganization).isEqualTo(expectedOrganization)
    }

    @Test
    fun asUserEntity_normalDtoUser_returnsUserEntity() {
        // GIVEN
        val userDto = fakeuserDto()
        val expectedUser = UserEntity(
            name = userDto.name,
            githubUsername = userDto.githubUsername,
            twitterUsername = userDto.twitterUsername,
            websiteUrl = userDto.websiteUrl,
            profileImage = userDto.profileImage,
        )

        // WHEN
        val mappedUser = userDto.asUserEntity()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asUserEntity_nullFieldsDtoUser_returnsUserEntityWithNullFields() {
        // GIVEN
        val userDto = UserDto(
            name = "sampleUserName",
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = "SampleProfileImage",
        )
        val expectedUser = UserEntity(
            name = userDto.name,
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = userDto.profileImage,
        )

        // WHEN
        val mappedUser = userDto.asUserEntity()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asArticleEntity_normalDtoArticle_returnsArticleEntity() {
        // GIVEN
        val instant = now.toInstant()
        val id = 1
        val user = fakeuserDto()
        val organizationDto = fakeOrganizationDto()
        val givenArticle = fakeArticleDto(
            isoTimestamp = instant.toString(),
            user = user,
            organization = organizationDto,
        )
        val expectedArticle = fakeEntityArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = UserEntity(
                name = user.name,
                githubUsername = user.githubUsername,
                twitterUsername = user.twitterUsername,
                websiteUrl = user.websiteUrl,
                profileImage = user.profileImage,
            ),
            organization = OrganizationEntity(
                name = organizationDto.name,
                profileImage = organizationDto.profileImage,
            ),
        )

        // WHEN
        val mappedArticle = givenArticle.asArticleEntity(id = id)

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticleEntity_nullOrganizationDtoArticle_returnsnullOrganizationArticleEntity() {
        // GIVEN
        val instant = now.toInstant()
        val id = 1
        val user = fakeuserDto()
        val givenArticle = fakeArticleDto(
            isoTimestamp = instant.toString(),
            user = user,
            organization = null,
        )
        val expectedArticle = fakeEntityArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = UserEntity(
                name = user.name,
                githubUsername = user.githubUsername,
                twitterUsername = user.twitterUsername,
                websiteUrl = user.websiteUrl,
                profileImage = user.profileImage,
            ),
            organization = null,
        )

        // WHEN
        val mappedArticle = givenArticle.asArticleEntity(id = id)

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }
}

private fun fakeuserDto(): UserDto = UserDto(
    name = "sampleUserName",
    githubUsername = "SampleGithubUsername",
    twitterUsername = "SampleTwitterUsername",
    websiteUrl = "SampleWebsiteUrl",
    profileImage = "SampleProfileImage",
)

private fun fakeOrganizationDto(): OrganizationDto = OrganizationDto(
    name = "SampleOrganizationName",
    profileImage = "SampleProfileImageUri",
)

private fun fakeArticleDto(isoTimestamp: String, user: UserDto, organization: OrganizationDto?) =
    ArticleDto(
        title = "SampleArticleTitle",
        description = "SampleArticleDescription",
        image = "SampleImageUrl",
        publishDate = isoTimestamp,
        url = "https://www.google.com",
        commentsCount = 10,
        reactionsCount = 1,
        readingMinutes = 1,
        language = "en",
        tags = listOf("tag1", "tag2"),
        user = user,
        organization = organization,
    )

private fun fakeEntityArticle(
    id: Int,
    timestamp: Long,
    user: UserEntity,
    organization: OrganizationEntity?,
) = ArticleEntity(
    id = id,
    title = "SampleArticleTitle",
    description = "SampleArticleDescription",
    image = "SampleImageUrl",
    publishDate = timestamp,
    url = "https://www.google.com",
    commentsCount = 10,
    reactionsCount = 1,
    readingMinutes = 1,
    language = "en",
    tags = listOf("tag1", "tag2"),
    user = user,
    organization = organization,
)
