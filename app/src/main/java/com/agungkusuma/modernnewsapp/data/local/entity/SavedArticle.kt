package com.agungkusuma.modernnewsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticle(
    val title: String?,
    @PrimaryKey val articleUrl: String,
    val publishedAt: String?,
    val imageUrl: String?,
    val savedAt: Long
)