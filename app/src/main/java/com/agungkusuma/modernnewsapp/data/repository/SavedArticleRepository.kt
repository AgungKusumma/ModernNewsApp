package com.agungkusuma.modernnewsapp.data.repository

import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle
import kotlinx.coroutines.flow.Flow

interface SavedArticleRepository {
    fun insertArticle(article: SavedArticle)
    fun getAllSavedArticles(): Flow<List<SavedArticle>>
}