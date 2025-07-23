package com.agungkusuma.modernnewsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.modernnewsapp.common.state.UiState
import com.agungkusuma.modernnewsapp.data.remote.model.Article
import com.agungkusuma.modernnewsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState<List<Article>>>(UiState.Idle)
    val searchState: StateFlow<UiState<List<Article>>> = _searchState

    fun searchNews(query: String) {
        viewModelScope.launch {
            _searchState.value = UiState.Loading
            try {
                val response = newsRepository.searchNews(query)
                _searchState.value = UiState.Success(response.articles)
            } catch (e: Exception) {
                _searchState.value = UiState.Error(e.message ?: "Unexpected error")
            }
        }
    }
}
