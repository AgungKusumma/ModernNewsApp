package com.agungkusuma.modernnewsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: SavedArticle)

    @Query("SELECT * FROM saved_articles ORDER BY savedAt DESC")
    fun getAllArticles(): Flow<List<SavedArticle>>
}
