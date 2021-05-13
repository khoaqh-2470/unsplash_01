package com.sun.unsplash_01.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sun.unsplash_01.extensions.loadFromUrl
import com.sun.unsplash_01.extensions.loadUrlStaggered
import com.sun.unsplash_01.utils.LoadMoreRecyclerViewListener

@BindingAdapter("onLoadImage")
fun ImageView.loadImage(url: String) {
    loadFromUrl(url)
}

@BindingAdapter("onLoadImageStaggered")
fun ImageView.loadImageStaggered(url: String) {
    loadUrlStaggered(url)
}

@BindingAdapter(value = ["adapter"])
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter(value = ["isLoading", "onLoadMore"])
fun RecyclerView.onScrollListener(
    isLoading: Boolean,
    loadMoreListener: LoadMoreRecyclerViewListener,
) {
    clearOnScrollListeners()
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                when (recyclerView.layoutManager) {
                    is LinearLayoutManager -> {
                        (recyclerView.layoutManager as LinearLayoutManager).run {
                            if (!isLoading && findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                                loadMoreListener.onLoadData()
                            }
                        }
                    }
                    is StaggeredGridLayoutManager -> {
                        (recyclerView.layoutManager as StaggeredGridLayoutManager).run {
                            val lastVisibleItemPosition =
                                getLastVisibleItem(findLastVisibleItemPositions(null))
                            if (!isLoading && (childCount + lastVisibleItemPosition) >= itemCount) {
                                loadMoreListener.onLoadData()
                            }
                        }
                    }
                }
            }
        }
    })
}

fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPositions.indices) {
        if (i == 0) {
            maxSize = lastVisibleItemPositions[i]
        } else if (lastVisibleItemPositions[i] > maxSize) {
            maxSize = lastVisibleItemPositions[i]
        }
    }
    return maxSize
}
