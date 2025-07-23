package com.agungkusuma.modernnewsapp.di

import android.content.Context
import androidx.room.Room
import com.agungkusuma.modernnewsapp.data.local.dao.SavedArticleDao
import com.agungkusuma.modernnewsapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSavedArticleDao(appDatabase: AppDatabase): SavedArticleDao {
        return appDatabase.savedArticleDao()
    }
}