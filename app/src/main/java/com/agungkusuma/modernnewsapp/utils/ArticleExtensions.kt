package com.agungkusuma.modernnewsapp.utils

import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle
import com.agungkusuma.modernnewsapp.data.remote.model.Article

fun SavedArticle.toArticle(): Article {
    return Article(
        newsTitle = this.title,
        articleUrl = this.articleUrl,
        publishedAt = this.publishedAt,
        imageUrl = this.imageUrl,
        authorName = null,
        summary = null,
        fullContent = null,
    )
}

fun Article.toSavedArticle(): SavedArticle {
    return SavedArticle(
        title = this.newsTitle,
        articleUrl = this.articleUrl.toString(),
        publishedAt = publishedAt,
        imageUrl = imageUrl,
        savedAt = System.currentTimeMillis(),
    )
}