package com.dicoding.dicodingeventapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.dicodingeventapp.core.ui.EventAdapter
import com.dicoding.dicodingeventapp.detail.DetailEventActivity
import com.dicoding.dicodingeventapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.apply {
            title = "Favorite"
            setDisplayHomeAsUpEnabled(true)
        }

        val eventAdapter = EventAdapter()
        eventAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteEvent.observe(this) { event ->
            eventAdapter.submitList(event)
        }

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = eventAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
