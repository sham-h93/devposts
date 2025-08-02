package com.hshamkhani.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hshamkhani.repository.model.RepoOrganization
import com.hshamkhani.repository.model.RepoUser

@Entity(tableName = "articles")
internal data class ArticleEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val publishDate: String,
    val url: String,
    val commentsCount: Int,
    val reactionsCount: Int,
    val readingMinutes: Int,
    val language: String,
    val tags: List<String>,
    val user: UserEntity,
    val organization: OrganizationEntity
)
