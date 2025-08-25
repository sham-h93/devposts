package com.hshamkhani.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.hshamkhani.designsystem.theme.DevPostsTheme

@Composable
fun ArticleProfile(
    modifier: Modifier = Modifier,
    userProfile: String,
    organizationProfile: String?,
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(percent = 50))
                .background(MaterialTheme.colorScheme.surface),
            model = userProfile,
            contentDescription = null,
        )
        organizationProfile?.let { profile ->
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(.4f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(percent = 50))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(percent = 50),
                    )
                    .background(MaterialTheme.colorScheme.surface),
                model = profile,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleProfilePreview() {
    DevPostsTheme {
        ArticleProfile(
            userProfile = "https://picsum.photos/200",
            organizationProfile = "https://picsum.photos/200",
        )
    }
}
