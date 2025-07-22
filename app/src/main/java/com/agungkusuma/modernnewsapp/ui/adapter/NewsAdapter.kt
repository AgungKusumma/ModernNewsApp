package com.agungkusuma.modernnewsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agungkusuma.modernnewsapp.R
import com.agungkusuma.modernnewsapp.data.remote.model.Article
import com.agungkusuma.modernnewsapp.databinding.ItemBigNewsBinding
import com.agungkusuma.modernnewsapp.utils.DateUtils
import com.bumptech.glide.Glide

class NewsAdapter(
    private val onItemClick: (Article) -> Unit
) : ListAdapter<Article, NewsAdapter.NewsViewHolder>(DiffCallback) {

    inner class NewsViewHolder(private val binding: ItemBigNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) = with(binding) {
            tvBigNewsTitle.text = item.newsTitle.orEmpty()
            tvBigNewsDate.text = DateUtils.formatDate(item.publishedAt.orEmpty())

            Glide.with(imgBigNews.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(imgBigNews)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemBigNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.articleUrl == newItem.articleUrl
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}