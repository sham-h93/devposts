package com.hshamkhani.articles.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.designsystem.theme.HotlineNewsTheme
import com.hshamkhani.designsystem.ui.BodyText
import com.hshamkhani.designsystem.ui.Image

@Composable
internal fun ArticleItem(
    article: UiArticle,
    onArticleClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable { onArticleClick() },
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                imageUri = article.urlToImage,
            )
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                        .padding(4.dp),
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style =
                    MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.background,
                    ),
            )
        }
        BodyText(
            text = article.description,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun ArticleItemPreview() {
    HotlineNewsTheme {
        Surface {
            ArticleItem(
                article =
                    UiArticle(
                        id = 1,
                        title = LoremIpsum(20).values.first(),
                        urlToImage = "",
                        description = LoremIpsum(20).values.first(),
                    ),
                onArticleClick = {},
            )
        }
    }
}
