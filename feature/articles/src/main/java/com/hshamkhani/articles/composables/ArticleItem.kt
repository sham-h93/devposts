package com.hshamkhani.articles.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.designsystem.theme.AppTheme
import com.hshamkhani.designsystem.ui.Image
import com.hshamkhani.designsystem.ui.Likes
import com.hshamkhani.designsystem.ui.Tags

@Composable
internal fun ArticleItem(article: UiArticle, onArticleClick: () -> Unit) {
    val colors = CardDefaults.cardColors().copy(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    )
    Card(
        colors = colors,
        onClick = onArticleClick,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier.size(56.dp),
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(percent = 50)),
                    imageUri = article.userProfileImage,
                )
                if (article.organizationProfileImage.isNotEmpty()) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(percent = 50))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(percent = 50),
                            ),
                        imageUri = article.organizationProfileImage,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            ) {
                Text(
                    text = article.publishers,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = article.publishDate,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                )
                if (article.image.isNotEmpty()) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 2f)
                            .clip(MaterialTheme.shapes.large),
                        imageUri = article.image,
                    )
                }
                Text(
                    text = article.articleTitle,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Tags(tags = article.tags)
                    Likes(count = article.reactionsCount)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ArticleItemPreview() {
    AppTheme {
        Surface {
            ArticleItem(
                article = UiArticle(
                    id = 1,
                    image = "offendit",
                    publishDate = "12 hours ago",
                    reactionsCount = 1229,
                    language = "quisque",
                    tags = listOf("taga", "tagtag", "ai"),
                    publishers = "instructior - hosseinx",
                    articleTitle = LoremIpsum(12).values.first(),
                    userProfileImage = "gravida",
                    organizationProfileImage = "nisi",
                ),
                onArticleClick = {},
            )
        }
    }
}
