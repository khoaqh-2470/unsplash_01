package com.sun.unsplash_01.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun.unsplash_01.ui.search.search_collection.SearchCollectionFragment
import com.sun.unsplash_01.ui.search.search_photo.SearchPhotoFragment
import com.sun.unsplash_01.utils.TypeSearch

class SearchAdapter(
    fragmentManager: FragmentManager,
    private var fragments: List<Fragment> = arrayListOf()
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TypeSearch.PHOTO.ordinal -> TypeSearch.PHOTO.name
            TypeSearch.COLLECTION.ordinal -> TypeSearch.COLLECTION.name
            else -> TypeSearch.PHOTO.name
        }
    }

    fun setKeyword(keyword: String) {
        fragments.forEach {
            when (it) {
                is SearchCollectionFragment -> it.searchCollection(keyword)
                is SearchPhotoFragment -> it.searchPhoto(keyword)
            }
        }
    }

    fun addFragments(fragments: List<Fragment>) {
        this.fragments = fragments
    }
}
