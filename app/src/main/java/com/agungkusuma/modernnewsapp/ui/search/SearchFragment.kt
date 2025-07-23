package com.agungkusuma.modernnewsapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.modernnewsapp.common.state.UiState
import com.agungkusuma.modernnewsapp.databinding.FragmentSearchBinding
import com.agungkusuma.modernnewsapp.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupSearchInput()
        observeSearchResult()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(article)
            findNavController().navigate(action)
        }
        binding.rvSearchResult.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun setupSearchInput() {
        binding.etSearch.addTextChangedListener { text ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500) // debounce
                val query = text.toString()
                if (query.isNotEmpty()) {
                    viewModel.searchNews(query)
                }
            }
        }
    }

    private fun observeSearchResult() {
        lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when (state) {
                    is UiState.Idle -> {}
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        newsAdapter.submitList(state.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchJob?.cancel()
        _binding = null
    }
}
