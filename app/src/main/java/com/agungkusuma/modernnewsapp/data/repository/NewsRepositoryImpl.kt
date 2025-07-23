package com.agungkusuma.modernnewsapp.data.repository

import com.agungkusuma.modernnewsapp.data.remote.api.NewsApiService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {

    override suspend fun getTopHeadlines() = apiService.getTopHeadlines()

    override suspend fun searchNews(query: String) = apiService.searchNews(query = query)
}