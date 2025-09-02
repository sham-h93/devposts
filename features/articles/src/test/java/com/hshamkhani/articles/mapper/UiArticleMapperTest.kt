package com.hshamkhani.articles.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.common.toReadableFormat
import com.hshamkhani.common.withHashtag
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import java.time.Instant
import org.junit.Before
import org.junit.Test

class UiArticleMapperTest {

    private lateinit var now: Instant

    @Before
    fun setUp() {
        now = Instant.now()
    }

    @Test
    fun asUiArticle_normalArticle_returnsNormalUiArticle() {
        // GIVEN
        val timeStamp = now.toEpochMilli()
        val article = fakeArticle(
            timestamp = timeStamp,
            user = fakeUser,
            organization = fakeOrganization,
        )
        val expectedArticle = UiArticle(
            id = article.id,
            publishers = "${article.user.name} - ${article.organization!!.name}",
            articleTitle = article.title,
            tags = article.tags.withHashtag(),
            publishDate = timeStamp.toReadableFormat(),
            image = article.image,
            reactionsCount = article.reactionsCount,
            language = article.language,
            userProfileImage = article.user.profileImage,
            organizationProfileImage = article.organization!!.profileImage,
        )

        // WHEN
        val mappedArticle = article.asUiArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asUiArticle_nullOrganizationArticle_returnsNullOrganizationUiArticle() {
        // GIVEN
        val timeStamp = now.toEpochMilli()
        val article = fakeArticle(
            timestamp = timeStamp,
            user = fakeUser,
            organization = null,
        )
        val expectedArticle = UiArticle(
            id = article.id,
            publishers = article.user.name,
            articleTitle = article.title,
            tags = article.tags.withHashtag(),
            publishDate = timeStamp.toReadableFormat(),
            image = article.image,
            reactionsCount = article.reactionsCount,
            language = article.language,
            userProfileImage = article.user.profileImage,
            organizationProfileImage = null,
        )

        // WHEN
        val mappedArticle = article.asUiArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asUiArticle_nullUserFieldsArticle_returnsNullUserFieldsUiArticle() {
        // GIVEN
        val timeStamp = now.toEpochMilli()
        val article = fakeArticle(
            timestamp = timeStamp,
            user = fakeUser.copy(
                githubUsername = null,
                twitterUsername = null,
                websiteUrl = null,
            ),
            organization = fakeOrganization,
        )
        val expectedArticle = UiArticle(
            id = article.id,
            publishers = "${article.user.name} - ${article.organization!!.name}",
            articleTitle = article.title,
            tags = article.tags.withHashtag(),
            publishDate = timeStamp.toReadableFormat(),
            image = article.image,
            reactionsCount = article.reactionsCount,
            language = article.language,
            userProfileImage = article.user.profileImage,
            organizationProfileImage = article.organization!!.profileImage,
        )

        // WHEN
        val mappedArticle = article.asUiArticle()

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
