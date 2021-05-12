package com.sun.unsplash_01.ui.photo_collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.unsplash_01.R
import com.sun.unsplash_01.data.model.PhotoCollection
import com.sun.unsplash_01.databinding.ItemLayoutPhotoBinding
import com.sun.unsplash_01.utils.OnItemClickListener

class PhotoCollectionAdapter : ListAdapter<PhotoCollection, PhotoCollectionViewHolder>(Companion) {

    private var onClickItemViewHolder: ((PhotoCollection) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCollectionViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutPhotoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout_photo, parent,
            false
        )
        return PhotoCollectionViewHolder(binding) { onClickItemViewHolder?.invoke(it) }.apply {
            registerOnItemClickListener()
        }
    }

    override fun onBindViewHolder(holder: PhotoCollectionViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    fun setOnClickItem(onClick: ((PhotoCollection) -> Unit)) {
        this.onClickItemViewHolder = onClick
    }

    companion object : DiffUtil.ItemCallback<PhotoCollection>() {

        override fun areItemsTheSame(oldItem: PhotoCollection, newItem: PhotoCollection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoCollection,
            newItem: PhotoCollection
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class PhotoCollectionViewHolder(
    private val binding: ItemLayoutPhotoBinding,
    private val onClickItemViewHolder: ((PhotoCollection) -> Unit)
) : RecyclerView.ViewHolder(binding.root),
    OnItemClickListener<PhotoCollection> {

    fun onBind(photoCollection: PhotoCollection) {
        binding.run {
            this.photoCollection = photoCollection
            executePendingBindings()
        }
    }

    fun registerOnItemClickListener() {
        binding.onItemClickListener = this
    }

    override fun onItemClick(item: PhotoCollection) {
        onClickItemViewHolder(item)
    }
}
