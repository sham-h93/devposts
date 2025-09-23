package com.hshamkhani.repository

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_domain.Result
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.Organization
import com.hshamkhani.domain.model.User
import com.hshamkhani.repository.datasource.ArticleDataSource
import com.hshamkhani.repository.model.RepoArticle
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException
import java.time.Instant
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ArticleRepositoryImplTest {

    private lateinit var articlesDataSource: ArticleDataSource
    private lateinit var articleRepository: ArticleRepositoryImpl

    private lateinit var instant: Instant

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        instant = Instant.now()
        articlesDataSource = mockk()
        articleRepository = ArticleRepositoryImpl(
            articleDataSource = articlesDataSource,
        )
    }

    @Test
    fun getArticleById_withGivenId_returnsArticle() = runTest(testDispatcher) {
        // GIVEN
        val articleId = 1
        val timestamp = instant.toEpochMilli()
        val givenArticle = fakeRepoArticle(
            id = articleId,
            isoTimestamp = timestamp,
            user = fakeRepoUser,
            organization = fakeRepoOrganization,
        )
        val article = fakeArticle(
            id = articleId,
            timestamp = timestamp,
            user = User(
                name = fakeRepoUser.name,
                githubUsername = fakeRepoUser.githubUsername,
                twitterUsername = fakeRepoUser.twitterUsername,
                websiteUrl = fakeRepoUser.websiteUrl,
                profileImage = fakeRepoUser.profileImage,
            ),
            organization = Organization(
                name = fakeRepoOrganization.name,
                profileImage = fakeRepoOrganization.profileImage,
            ),
        )

        val expectedResult = Result.Success(data = article)

        // WHEN
        coEvery { articlesDataSource.getArticleById(id = articleId) } returns givenArticle
        val result = articleRepository.getArticleById(id = articleId)

        // THEN
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun getArticleById_withInvalidId_returnsError() = runTest(testDispatcher) {
        // GIVEN
        val articleId = -1
        val error = Error.Local.IO
        val expectedResult = Result.Failure(error = error)

        // WHEN
        coEvery { articlesDataSource.getArticleById(id = articleId) } throws
            IOException("Sample exception")
        val result = articleRepository.getArticleById(id = articleId)

        // THEN
        assertThat(result).isEqualTo(expectedResult)
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

private fun fakeRepoArticle(
    id: Int,
    isoTimestamp: Long,
    user: RepoUser,
    organization: RepoOrganization?,
) = RepoArticle(
    id = id,
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

private fun fakeArticle(id: Int, timestamp: Long, user: User, organization: Organization?) =
    Article(
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
