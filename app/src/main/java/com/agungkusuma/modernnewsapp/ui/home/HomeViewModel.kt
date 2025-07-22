package com.agungkusuma.modernnewsapp.ui.home

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
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val newsState: StateFlow<UiState<List<Article>>> = _newsState

    fun fetchNews() {
        viewModelScope.launch {
            _newsState.value = UiState.Loading
            try {
                val response = repository.getTopHeadlines()
                _newsState.value = UiState.Success(response.articles)
            } catch (e: Exception) {
                _newsState.value = UiState.Error(e.message)
            }
        }
    }
}
