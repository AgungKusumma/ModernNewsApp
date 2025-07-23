package com.agungkusuma.modernnewsapp.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.modernnewsapp.data.local.entity.SavedArticle
import com.agungkusuma.modernnewsapp.data.repository.SavedArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: SavedArticleRepository
) : ViewModel() {

    val savedArticles = repository.getAllSavedArticles()
        .map { list -> list.sortedByDescending { it.savedAt } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveArticle(article: SavedArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticle(article)
        }
    }
}