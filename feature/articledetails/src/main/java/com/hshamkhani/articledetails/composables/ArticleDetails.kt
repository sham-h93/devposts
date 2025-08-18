package com.hshamkhani.articledetails.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.hshamkhani.articledetails.R
import com.hshamkhani.articledetails.model.UiArticle
import com.hshamkhani.articledetails.model.UiOrganization
import com.hshamkhani.articledetails.model.UiUser
import com.hshamkhani.designsystem.theme.AppTheme
import com.hshamkhani.designsystem.ui.ArticleImage
import com.hshamkhani.designsystem.ui.ArticleProfile
import com.hshamkhani.designsystem.ui.IconButton
import com.hshamkhani.designsystem.ui.Reactions
import com.hshamkhani.designsystem.ui.Tags

@Composable
internal fun ArticleDetails(modifier: Modifier = Modifier, article: UiArticle) {
    val context = LocalContext.current
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
                organizationProfile = article.organization?.profileImage?.takeIf {
                    it.isNotEmpty()
                },
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                UserDetails(article = article, context = context)
                article.organization?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
        ArticleImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.large),
            imageUri = article.image,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(
                    id = R.string.article_details_published_at,
                    article.publishDate,
                ),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = stringResource(
                    id = R.string.article_details_mins_read,
                    article.readingMinutes,
                ),
                style = MaterialTheme.typography.labelSmall,
            )
            Reactions(
                icon = Icons.Default.Favorite,
                iconTint = MaterialTheme.colorScheme.error,
                count = article.reactionsCount,
            )
            if (article.commentsCount > 0) {
                Reactions(
                    icon = ImageVector.vectorResource(id = R.drawable.article_comment_ic),
                    iconTint = MaterialTheme.colorScheme.surfaceDim,
                    count = article.commentsCount,
                )
            }
        }
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
        )
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
            onClick = {
                context.sendIntent(uri = article.url.toUri())
            },
        ) {
            Text(
                text = "Read full article",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun UserDetails(article: UiArticle, context: Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = article.user.name,
            style = MaterialTheme.typography.bodyLarge,
        )
        article.user.githubUsername?.let { username ->
            IconButton(
                modifier = Modifier.size(30.dp),
                icon = ImageVector.vectorResource(id = R.drawable.article_github_icon),
                onClick = {
                    context.sendIntent(
                        uri = context.getString(R.string.article_user_github_uri, username)
                            .toUri(),
                    )
                },
            )
        }
        article.user.twitterUsername?.let { username ->
            IconButton(
                modifier = Modifier.size(30.dp),
                icon = ImageVector.vectorResource(id = R.drawable.article_x_icon),
                onClick = {
                    context.sendIntent(
                        uri = context.getString(R.string.article_user_x_uri, username).toUri(),
                    )
                },
            )
        }
        article.user.websiteUrl?.let { webSite ->
            IconButton(
                modifier = Modifier.size(30.dp),
                icon = ImageVector.vectorResource(id = R.drawable.article_web_icon),
                onClick = {
                    context.sendIntent(
                        uri = webSite.toUri(),
                    )
                },
            )
        }
    }
}

private fun Context.sendIntent(uri: Uri) = startActivity(Intent(Intent.ACTION_VIEW, uri))

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
                readingMinutes = 3,
                language = "tincidunt",
                tags = listOf("taga", "tagb", "tagged"),
                user = UiUser(
                    name = "Rodrigo King",
                    githubUsername = "Taylor Harrell",
                    twitterUsername = "Nelda Franklin",
                    websiteUrl = "https://www.google.com/#q=constituam",
                    profileImage = "https://picsum.photos/300",
                ),
                organization = UiOrganization(
                    name = "Reyna Miles",
                    profileImage = "https://picsum.photos/300",
                ),
            ),
        )
    }
}
