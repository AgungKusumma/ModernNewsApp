package com.agungkusuma.modernnewsapp.data.repository

import com.agungkusuma.modernnewsapp.data.local.dao.SavedArticleDao
import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedArticleRepositoryImpl @Inject constructor(
    private val dao: SavedArticleDao,
) : SavedArticleRepository {

    override fun insertArticle(article: SavedArticle) {
        dao.insertArticle(article)
    }

    override fun getAllSavedArticles(): Flow<List<SavedArticle>> {
        return dao.getAllArticles()
    }
}
