package com.sun.unsplash_01.ui.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal

class FavoriteDiffUtil: DiffUtil.ItemCallback<ImageLocal>() {

    override fun areItemsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean =
        oldItem == newItem
}
