package com.hshamkhani.articles.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.hshamkhani.articles.model.UiOrganization
import com.hshamkhani.articles.model.UiUser
import com.hshamkhani.designsystem.theme.AppTheme
import com.hshamkhani.designsystem.ui.Image
import com.hshamkhani.designsystem.ui.backgroundGradient

@Composable
internal fun ArticleItem(article: UiArticle, onArticleClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onArticleClick() },
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceContainer),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
                imageUri = article.image,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        backgroundGradient(color = MaterialTheme.colorScheme.background),
                    )
                    .padding(top = 30.dp)
                    .padding(12.dp),
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displaySmall,
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
        )
    }
}

@Preview
@Composable
private fun ArticleItemPreview() {
    AppTheme {
        Surface {
            ArticleItem(
                article =
                    UiArticle(
                        id = 1,
                        title = LoremIpsum(20).values.first(),
                        description = LoremIpsum(20).values.first(),
                        image = "offendit",
                        publishDate = "dicit",
                        reactionsCount = 1229,
                        language = "quisque",
                        tags = listOf(),
                        user = UiUser(
                            name = "Norma Hendricks",
                            username = "Kris Barlow",
                            profileImage = "semper",
                        ),
                        organization = UiOrganization(
                            name = "Carmen McCoy",
                            profileImage = "possit",
                        ),
                    ),
                onArticleClick = {},
            )
        }
    }
}
