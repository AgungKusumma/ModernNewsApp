package com.agungkusuma.modernnewsapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.agungkusuma.modernnewsapp.R
import com.agungkusuma.modernnewsapp.databinding.FragmentDetailBinding
import com.agungkusuma.modernnewsapp.utils.DateUtils.formatDate
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article

        binding.apply {
            tvTitle.text = article.newsTitle.orEmpty()
            tvAuthor.text = article.authorName ?: getString(R.string.author_unknown)
            tvDescription.text = article.summary ?: getString(R.string.no_description)
            tvContent.text = article.fullContent ?: getString(R.string.no_content)
            tvDate.text = article.publishedAt?.let { formatDate(it) } ?: ""

            Glide.with(imgNews.context)
                .load(article.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(imgNews)

            btnOpenUrl.setOnClickListener {
                article.articleUrl?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
