package com.dicoding.dicodingeventapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.dicodingeventapp.core.databinding.ItemListEventBinding
import com.dicoding.dicodingeventapp.core.domain.model.Event

class EventAdapter : ListAdapter<Event, EventAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Event) -> Unit)? = null
    private lateinit var binding : ItemListEventBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Event) {
            Glide.with(itemView.context)
                .load(data.mediaCover)
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = data.name
            binding.tvSchedule.text = data.beginTime
            binding.tvOwnerEvent.text = data.ownerName
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Event> =
            object : DiffUtil.ItemCallback<Event>() {
                override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem == newItem
                }
            }
    }
}