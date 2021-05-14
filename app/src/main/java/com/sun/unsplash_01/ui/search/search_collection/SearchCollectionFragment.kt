package com.sun.unsplash_01.ui.search.search_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.unsplash_01.databinding.FragmentSearchCollectionBinding

class SearchCollectionFragment : Fragment() {

    lateinit var binding: FragmentSearchCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun searchCollection(keyWord: String) {
    }

    companion object {
        fun newInstance() = SearchCollectionFragment()
    }
}
