package com.hshamkhani.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser
import java.time.ZonedDateTime
import org.junit.Before
import org.junit.Test

internal class EntityMapperTest {
    private lateinit var now: ZonedDateTime

    @Before
    fun setUp() {
        now = ZonedDateTime.now()
    }

    @Test
    fun asRepoOrganization_normalEntityOrganization_returnsRepoOrganization() {
        // GIVEN
        val organizationEntity = fakeOrganizationEntity()
        val expectedOrganization = RepoOrganization(
            name = organizationEntity.name,
            profileImage = organizationEntity.profileImage,
        )

        // WHEN
        val mappedOrganization = organizationEntity.asRepoOrganization()

        // THEN
        assertThat(mappedOrganization).isEqualTo(expectedOrganization)
    }

    @Test
    fun asRepoUser_normalUserEntity_returnsUserEntity() {
        // GIVEN
        val userEntity = fakeUserEntity()
        val expectedUser = RepoUser(
            name = userEntity.name,
            githubUsername = userEntity.githubUsername,
            twitterUsername = userEntity.twitterUsername,
            websiteUrl = userEntity.websiteUrl,
            profileImage = userEntity.profileImage,

        )
        // WHEN
        val mappedUser = userEntity.asRepoUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asRepoUser_nullFieldsUserEntity_returnsNullFieldsUserEntity() {
        // GIVEN
        val userEntity = UserEntity(
            name = "sampleUserName",
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = "SampleProfileImage",
        )
        val expectedUser = RepoUser(
            name = userEntity.name,
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = userEntity.profileImage,
        )
        // WHEN
        val mappedUser = userEntity.asRepoUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asRepoArticle_normalEntityArticle_returnsRepoArticle() {
        // GIVEN
        val instant = now.toInstant()
        val id = 1
        val user = fakeUserEntity()
        val organization = fakeOrganizationEntity()
        val entityArticle = fakeEntityArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = user,
            organization = organization,
        )
        val expectedArticle = fakeRepoArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = RepoUser(
                name = user.name,
                githubUsername = user.githubUsername,
                twitterUsername = user.twitterUsername,
                websiteUrl = user.websiteUrl,
                profileImage = user.profileImage,
            ),
            organization = RepoOrganization(
                name = organization.name,
                profileImage = organization.profileImage,
            ),
        )

        // WHEN
        val mappedArticle = entityArticle.asRepoArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asRepoArticle_nullOrganizationEntityArticle_returnsNullOrganizationRepoArticle() {
        // GIVEN
        val instant = now.toInstant()
        val id = 1
        val user = fakeUserEntity()
        val entityArticle = fakeEntityArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = user,
            organization = null,
        )
        val expectedArticle = fakeRepoArticle(
            id = id,
            timestamp = instant.toEpochMilli(),
            user = RepoUser(
                name = user.name,
                githubUsername = user.githubUsername,
                twitterUsername = user.twitterUsername,
                websiteUrl = user.websiteUrl,
                profileImage = user.profileImage,
            ),
            organization = null,
        )

        // WHEN
        val mappedArticle = entityArticle.asRepoArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }
}

private fun fakeUserEntity(): UserEntity = UserEntity(
    name = "sampleUserName",
    githubUsername = "SampleGithubUsername",
    twitterUsername = "SampleTwitterUsername",
    websiteUrl = "SampleWebsiteUrl",
    profileImage = "SampleProfileImage",
)

private fun fakeOrganizationEntity(): OrganizationEntity = OrganizationEntity(
    name = "SampleOrganizationName",
    profileImage = "SampleProfileImageUri",
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

private fun fakeRepoArticle(
    id: Int,
    timestamp: Long,
    user: RepoUser,
    organization: RepoOrganization?,
) = RepoArticle(
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
