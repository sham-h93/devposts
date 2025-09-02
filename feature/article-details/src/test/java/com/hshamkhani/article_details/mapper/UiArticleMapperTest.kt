package com.hshamkhani.article_details.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.article_details.model.UiArticle
import com.hshamkhani.article_details.model.UiOrganization
import com.hshamkhani.article_details.model.UiUser
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import java.time.Instant
import org.junit.Before
import org.junit.Test

internal class UiArticleMapperTest {
    private lateinit var now: Instant

    @Before
    fun setUp() {
        now = Instant.now()
    }

    @Test
    fun asUiOrganization_normalOrganization_returnsNormalOrganization() {
        // GIVEN
        val organization = fakeOrganization
        val expectedOrganization = UiOrganization(
            name = organization.name,
            profileImage = organization.profileImage,
        )

        // WHEN
        val mappedOrganization = organization.asUiOrganization()

        // THEN
        assertThat(mappedOrganization).isEqualTo(expectedOrganization)
    }

    @Test
    fun asUiUser_normalUser_returnsNormalUser() {
        // GIVEN
        val user = fakeUser
        val expectedUser = UiUser(
            name = user.name,
            githubUsername = user.githubUsername,
            twitterUsername = user.twitterUsername,
            websiteUrl = user.websiteUrl,
            profileImage = user.profileImage,
        )

        // WHEN
        val mappedUser = user.asUiUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asUiUser_nullFieldsUser_returnsNullFieldsUser() {
        // GIVEN
        val user = fakeUser.copy(
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
        )
        val expectedUser = UiUser(
            name = user.name,
            githubUsername = null,
            twitterUsername = null,
            websiteUrl = null,
            profileImage = user.profileImage,
        )

        // WHEN
        val mappedUser = user.asUiUser()

        // THEN
        assertThat(mappedUser).isEqualTo(expectedUser)
    }

    @Test
    fun asUiArticle_normalArticle_returnsNormalArticle() {
        // WHEN
        val timeStamp = now.toEpochMilli()
        val readableTime = timeStamp.toReadableFormat()
        val givenArticle = fakeArticle(
            timestamp = timeStamp,
            user = fakeUser,
            organization = fakeOrganization,
        )
        val expectedArticle = fakeUiArticle(
            readableTime = readableTime,
            user = UiUser(
                name = givenArticle.user.name,
                githubUsername = givenArticle.user.githubUsername,
                twitterUsername = givenArticle.user.twitterUsername,
                websiteUrl = givenArticle.user.websiteUrl,
                profileImage = givenArticle.user.profileImage,
            ),
            organization = UiOrganization(
                name = givenArticle.organization!!.name,
                profileImage = givenArticle.organization!!.profileImage,
            ),
        )

        // WHEN
        val mappedArticle = givenArticle.asUiArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asUiArticle_nullOrganizationArticle_returnsNullOrganizationArticle() {
        // WHEN
        val timeStamp = now.toEpochMilli()
        val readableTime = timeStamp.toReadableFormat()
        val givenArticle = fakeArticle(
            timestamp = timeStamp,
            user = fakeUser,
            organization = null,
        )
        val expectedArticle = fakeUiArticle(
            readableTime = readableTime,
            user = UiUser(
                name = givenArticle.user.name,
                githubUsername = givenArticle.user.githubUsername,
                twitterUsername = givenArticle.user.twitterUsername,
                websiteUrl = givenArticle.user.websiteUrl,
                profileImage = givenArticle.user.profileImage,
            ),
            organization = null,
        )

        // WHEN
        val mappedArticle = givenArticle.asUiArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }
}
private val fakeUser = User(
    name = "sampleUserName",
    githubUsername = "SampleGithubUsername",
    twitterUsername = "SampleTwitterUsername",
    websiteUrl = "SampleWebsiteUrl",
    profileImage = "SampleProfileImage",
)

private val fakeOrganization = Organization(
    name = "SampleOrganizationName",
    profileImage = "SampleProfileImageUri",
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

private fun fakeUiArticle(
    readableTime: String,
    user: UiUser,
    organization: UiOrganization?,
): UiArticle = UiArticle(
    id = 1,
    title = "SampleArticleTitle",
    description = "SampleArticleDescription",
    image = "SampleImageUrl",
    publishDate = readableTime,
    url = "https://www.google.com",
    commentsCount = 10,
    reactionsCount = 1,
    readingMinutes = 1,
    language = "en",
    tags = listOf("tag1", "tag2"),
    user = user,
    organization = organization,
)
