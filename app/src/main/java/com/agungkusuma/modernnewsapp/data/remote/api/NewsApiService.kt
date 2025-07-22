package com.agungkusuma.modernnewsapp.data.remote.api

import com.agungkusuma.modernnewsapp.BuildConfig.NEWS_API_KEY
import com.agungkusuma.modernnewsapp.data.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsResponse
}
