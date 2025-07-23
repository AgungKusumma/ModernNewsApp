package com.agungkusuma.modernnewsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agungkusuma.modernnewsapp.data.local.dao.SavedArticleDao
import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle

@Database(entities = [SavedArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedArticleDao(): SavedArticleDao
}