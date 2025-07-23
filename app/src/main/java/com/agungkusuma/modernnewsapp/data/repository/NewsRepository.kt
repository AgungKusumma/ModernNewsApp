package com.agungkusuma.modernnewsapp.data.repository

import com.agungkusuma.modernnewsapp.data.remote.model.NewsResponse

interface NewsRepository {
    suspend fun getTopHeadlines(): NewsResponse
    suspend fun searchNews(query: String): NewsResponse
}