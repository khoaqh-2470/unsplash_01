package com.sun.unsplash_01.utils

import androidx.recyclerview.widget.DiffUtil
import com.sun.unsplash_01.data.models.Topic

class TopicDiffUtilCallBack :
    DiffUtil.ItemCallback<Topic?>() {

    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem == newItem
    }
}
