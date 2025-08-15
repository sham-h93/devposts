package com.hshamkhani.articledetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.hshamkhani.articledetails.R
import com.hshamkhani.articledetails.model.UiArticle
import com.hshamkhani.articledetails.model.UiOrganization
import com.hshamkhani.articledetails.model.UiUser
import com.hshamkhani.designsystem.theme.AppTheme
import com.hshamkhani.designsystem.ui.ArticleProfile
import com.hshamkhani.designsystem.ui.ImageState
import com.hshamkhani.designsystem.ui.Reactions
import com.hshamkhani.designsystem.ui.Tags

@Composable
internal fun ArticleDetails(modifier: Modifier = Modifier, article: UiArticle) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArticleProfile(
                modifier = Modifier.size(64.dp),
                userProfile = article.user.profileImage,
                organizationProfile = article.organization.profileImage.takeIf { it.isNotEmpty() },
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(text = article.user.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = article.organization.name, style = MaterialTheme.typography.bodySmall)
            }
        }
        ImageState(
            modifier = Modifier.fillMaxWidth().clip(shape = MaterialTheme.shapes.large),
            imageUri = article.image,
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.article_details_published_at, article.publishDate),
                style = MaterialTheme.typography.labelSmall,
            )
            Reactions(
                icon = Icons.Default.Favorite,
                iconTint = MaterialTheme.colorScheme.error,
                count = article.reactionsCount,
            )
            Reactions(
                icon = ImageVector.vectorResource(id = R.drawable.article_comment_ic),
                iconTint = MaterialTheme.colorScheme.surfaceDim,
                count = article.commentsCount,
            )
        }
        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
        )

        Tags(
            tags = article.tags,
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = { },
        ) {
            Text(
                text = "Read full article",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleDetailsPreview() {
    AppTheme {
        ArticleDetails(
            modifier = Modifier.padding(8.dp),
            article = UiArticle(
                id = 3850,
                title = LoremIpsum(7).values.first(),
                description = LoremIpsum(50).values.first(),
                image = "https://picsum.photos/1200",
                publishDate = "14 Hour's ago",
                url = "https://search.yahoo.com/search?p=rutrum",
                commentsCount = 9195,
                reactionsCount = 2228,
                readingMinutes = 1776,
                language = "tincidunt",
                tags = listOf("taga", "tagb", "tagged"),
                user = UiUser(
                    name = "Rodrigo King",
                    username = "Chuck Kirkland",
                    githubUsername = "Taylor Harrell",
                    twitterUsername = "Nelda Franklin",
                    websiteUrl = "https://www.google.com/#q=constituam",
                    profileImage = "https://picsum.photos/300",
                ),
                organization = UiOrganization(
                    name = "Reyna Miles",
                    username = "Monique Steele",
                    profileImage = "https://picsum.photos/300",
                ),
            ),
        )
    }
}
