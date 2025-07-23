package com.agungkusuma.modernnewsapp.di

import com.agungkusuma.modernnewsapp.data.repository.NewsRepository
import com.agungkusuma.modernnewsapp.data.repository.NewsRepositoryImpl
import com.agungkusuma.modernnewsapp.data.repository.SavedArticleRepository
import com.agungkusuma.modernnewsapp.data.repository.SavedArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    @Singleton
    abstract fun bindSavedArticleRepository(
        impl: SavedArticleRepositoryImpl
    ): SavedArticleRepository
}