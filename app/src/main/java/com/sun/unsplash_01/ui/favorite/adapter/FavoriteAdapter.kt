package com.sun.unsplash_01.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.sun.unsplash_01.R
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal
import com.sun.unsplash_01.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onFavoritePhotoClick: (ImageLocal) -> Unit
) : ListAdapter<ImageLocal, FavoriteViewHolder>(FavoriteDiffUtil()) {

    private fun createBinding(parent: ViewGroup): ViewDataBinding =
        DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false
        ).apply {
            root.setOnClickListener {
                this.imageLocal?.let { item ->
                    onFavoritePhotoClick(item)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(createBinding(parent))

    private fun bind(binding: ViewDataBinding, item: ImageLocal) {
        if (binding is ItemFavoriteBinding) binding.imageLocal = item
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }
}
