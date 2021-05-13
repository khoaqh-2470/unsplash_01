package com.sun.unsplash_01.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.sun.unsplash_01.R
import com.sun.unsplash_01.data.model.PhotoCollection
import com.sun.unsplash_01.databinding.ItemSlideHomeBinding
import com.sun.unsplash_01.extensions.loadFromUrl

class HomeSlideAdapter(
    context: Context,
    private val images: MutableList<PhotoCollection>,
    private val onItemClick: (String) -> Unit
) : PagerAdapter() {

    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var binding: ItemSlideHomeBinding

    override fun getCount() = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as CardView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_slide_home, container, false)
        binding.imageViewSlide.apply {
            loadFromUrl(images[position].image.regular)
            setOnClickListener { onItemClick(images[position].id) }
        }
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}
