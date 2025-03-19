package com.dicoding.dicodingeventapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.dicoding.dicodingeventapp.R
import com.dicoding.dicodingeventapp.core.domain.model.Event
import com.dicoding.dicodingeventapp.databinding.ActivityDetailEventBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailEventActivity : AppCompatActivity() {

   private val detailEventViewModel: DetailEventViewModel by viewModel()
    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val detailEvent = getParcelableExtra(intent, EXTRA_DATA, Event::class.java)
        showDetailTourism(detailEvent)
    }

    private fun showDetailTourism(detailEvent: Event?) {
        detailEvent?.let {
            supportActionBar?.title = detailEvent.name
            val description = detailEvent.description
            binding.contentDetailEvent.tvDetailDescription.text = "Deskripsi event:\n${HtmlCompat.fromHtml(description ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)}"


            Glide.with(this@DetailEventActivity)
                .load(detailEvent.mediaCover)
                .into(binding.ivDetailImage)

            var statusFavorite = detailEvent.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailEventViewModel.setFavoriteEvent(detailEvent, statusFavorite)
                setStatusFavorite(statusFavorite)

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
