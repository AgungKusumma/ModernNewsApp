package com.agungkusuma.modernnewsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val authorName: String?,

    @SerializedName("title")
    val newsTitle: String?,

    @SerializedName("description")
    val summary: String?,

    @SerializedName("url")
    val articleUrl: String?,

    @SerializedName("urlToImage")
    val imageUrl: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val fullContent: String?
)