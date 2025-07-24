package com.hshamkhani.articles

import androidx.paging.PagingData
import com.hshamkhani.articles.model.UiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewsArticlesState(
    val isLoading: Boolean = true,
    val articles: Flow<PagingData<UiArticle>> = emptyFlow(),
)
