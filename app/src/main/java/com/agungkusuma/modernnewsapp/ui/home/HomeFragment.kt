package com.agungkusuma.modernnewsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.modernnewsapp.common.state.UiState
import com.agungkusuma.modernnewsapp.databinding.FragmentHomeBinding
import com.agungkusuma.modernnewsapp.ui.adapter.NewsAdapter
import com.agungkusuma.modernnewsapp.ui.history.HistoryViewModel
import com.agungkusuma.modernnewsapp.utils.toSavedArticle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeNews()
        viewModel.fetchNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            historyViewModel.saveArticle(article.toSavedArticle())
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(article)
            findNavController().navigate(action)
        }
        binding.rvBigNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observeNews() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsState.collect { state ->
                when (state) {
                    is UiState.Idle -> {}
                    is UiState.Loading -> {
                        rvBigNews.visibility = View.GONE
                        shimmerLayout.visibility = View.VISIBLE
                    }

                    is UiState.Success -> {
                        shimmerLayout.visibility = View.GONE
                        rvBigNews.visibility = View.VISIBLE
                        newsAdapter.submitList(state.data)
                    }

                    is UiState.Error -> {
                        shimmerLayout.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}