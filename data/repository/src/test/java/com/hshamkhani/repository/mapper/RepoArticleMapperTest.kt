package com.hshamkhani.repository.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser
import java.time.Instant
import org.junit.Before
import org.junit.Test

internal class RepoArticleMapperTest {
    private lateinit var now: Instant

    @Before
    fun setUp() {
        now = Instant.now()
    }

    @Test
    fun asOrganization_normalOrganization_returnsOrganization() {
        // GIVEN
        val repoOrganization = fakeRepoOrganization
        val expectedOrganization = Organization(
            name = repoOrganization.name,
            profileImage = repoOrganization.profileImage,
        )

        // WHEN
        val mappedOrganization = repoOrganization.asOrganization()

        // THEN
        assertThat(mappedOrganization).isEqualTo(expectedOrganization)
    }

    @Test
    fun asUser_normalRepoUser_returnsUser() {
        // GIVEN
        val repoUser = fakeRepoUser
        val expectedUser = User(
            name = repoUser.name,
            githubUsername = repoUser.githubUsername,
            twitterUsername = repoUser.twitterUsername,
            websiteUrl = repoUser.websiteUrl,
            profileImage = repoUser.profileImage,
        )

        // WHEN
        val mappedUser = repoUser.asUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asUser_nullFieldsRepoUser_returnsNullFieldsUser() {
        // GIVEN
        val repoUser = fakeRepoUser.copy(
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
        )
        val expectedUser = User(
            name = repoUser.name,
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = repoUser.profileImage,
        )

        // WHEN
        val mappedUser = repoUser.asUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asArticle_normalRepoArticle_returnsArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle(
            timestamp = now.toEpochMilli(),
            organization = fakeRepoOrganization,
        )
        val user = User(
            name = givenArticle.user.name,
            githubUsername = givenArticle.user.githubUsername,
            twitterUsername = givenArticle.user.twitterUsername,
            websiteUrl = givenArticle.user.websiteUrl,
            profileImage = givenArticle.user.profileImage,
        )
        val organization = Organization(
            name = givenArticle.organization!!.name,
            profileImage = givenArticle.organization.profileImage,
        )
        val expectedArticle = fakeArticle(
            timestamp = now.toEpochMilli(),
            user = user,
            organization = organization,
        )

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticle_nullOrganizationArticle_returnsNullOrganizationArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle(
            timestamp = now.toEpochMilli(),
            organization = null,
        )
        val user = User(
            name = givenArticle.user.name,
            githubUsername = givenArticle.user.githubUsername,
            twitterUsername = givenArticle.user.twitterUsername,
            websiteUrl = givenArticle.user.websiteUrl,
            profileImage = givenArticle.user.profileImage,
        )
        val expectedArticle = fakeArticle(
            timestamp = now.toEpochMilli(),
            user = user,
            organization = null,
        )

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }
}

private val fakeRepoUser = RepoUser(
    name = "sampleUserName",
    githubUsername = "SampleGithubUsername",
    twitterUsername = "SampleTwitterUsername",
    websiteUrl = "SampleWebsiteUrl",
    profileImage = "SampleProfileImage",
)

private val fakeRepoOrganization = RepoOrganization(
    name = "SampleOrganizationName",
    profileImage = "SampleProfileImageUri",
)

private fun fakeRepoArticle(timestamp: Long, organization: RepoOrganization?): RepoArticle =
    RepoArticle(
        id = 1,
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
        user = fakeRepoUser,
        organization = organization,
    )

private fun fakeArticle(timestamp: Long, user: User, organization: Organization?): Article =
    Article(
        id = 1,
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
