package com.hshamkhani.repository.mapper

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser
import org.junit.Test

internal class RepoArticleMapperTest {
    @Test
    fun asArticle_normalRepoArticle_returnsArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle
        val expectedArticle = fakeArticle

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticle_nullOrganizationArticle_returnsNullOrganizationArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle.copy(organization = null)
        val expectedArticle = fakeArticle.copy(organization = null)

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticle_nullGithubUserName_returnsNullGithubUserNameArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle.apply { user.copy(githubUsername = null) }
        val expectedArticle = fakeArticle.apply { user.copy(githubUsername = null) }

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticle_nullTwitterUserName_returnNullTwitterUserNameArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle.apply { user.copy(twitterUsername = null) }
        val expectedArticle = fakeArticle.apply { user.copy(twitterUsername = null) }

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }

    @Test
    fun asArticle_nullWebsiteUrl_returnsNullWebsiteUrlArticle() {
        // GIVEN
        val givenArticle = fakeRepoArticle.apply { user.copy(websiteUrl = null) }
        val expectedArticle = fakeArticle.apply { user.copy(websiteUrl = null) }

        // WHEN
        val mappedArticle = givenArticle.asArticle()

        // THEN
        assertThat(mappedArticle).isEqualTo(expectedArticle)
    }
}

private val fakeRepoArticle = RepoArticle(
    id = 1,
    title = "SampleArticleTitle",
    description = "SampleArticleDescription",
    image = "SampleImageUrl",
    publishDate = 123456,
    url = "https://www.google.com",
    commentsCount = 10,
    reactionsCount = 1,
    readingMinutes = 1,
    language = "en",
    tags = listOf("tag1", "tag2"),
    user = RepoUser(
        name = "sampleUserName",
        githubUsername = "SampleGithubUserName",
        twitterUsername = "SampleTwitterUserName",
        websiteUrl = "SampleWebsiteUrl",
        profileImage = "SampleProfileImage",
    ),
    organization = RepoOrganization(
        name = "SampleOrganizationName",
        profileImage = "SampleProfileImageUri",
    ),
)

private val fakeArticle = Article(
    id = 1,
    title = "SampleArticleTitle",
    description = "SampleArticleDescription",
    image = "SampleImageUrl",
    publishDate = 123456,
    url = "https://www.google.com",
    commentsCount = 10,
    reactionsCount = 1,
    readingMinutes = 1,
    language = "en",
    tags = listOf("tag1", "tag2"),
    user = User(
        name = "sampleUserName",
        githubUsername = "SampleGithubUserName",
        twitterUsername = "SampleTwitterUserName",
        websiteUrl = "SampleWebsiteUrl",
        profileImage = "SampleProfileImage",
    ),
    organization = Organization(
        name = "SampleOrganizationName",
        profileImage = "SampleProfileImageUri",
    ),
)
