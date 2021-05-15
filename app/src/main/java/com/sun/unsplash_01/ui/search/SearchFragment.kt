package com.sun.unsplash_01.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sun.unsplash_01.BuildConfig.APPLICATION_ID
import com.sun.unsplash_01.databinding.FragmentSearchBinding
import com.sun.unsplash_01.ui.search.search_collection.SearchCollectionFragment
import com.sun.unsplash_01.ui.search.search_photo.SearchPhotoFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchManager: SearchManager
    private lateinit var suggestions: SearchRecentSuggestions
    private val searchAdapter by lazy { SearchAdapter(childFragmentManager) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerService()
        initTab()
        handleEvent()
        binding()
    }

    private fun handleEvent() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchAdapter.setKeyword(it) }
                suggestions.saveRecentQuery(query, null)
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        binding.searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {

            override fun onSuggestionSelect(position: Int) = true

            override fun onSuggestionClick(position: Int): Boolean {
                binding.searchView.setQuery(getQuerySearchView(position), true)
                return true
            }
        })
    }

    private fun initTab() {
        val listFragment = listOf(
            SearchPhotoFragment.newInstance(),
            SearchCollectionFragment.newInstance()
        )
        binding.run {
            viewPagerSearch.adapter = searchAdapter.apply {
                addFragments(listFragment)
            }
            tabLayout.setupWithViewPager(binding.viewPagerSearch)
        }
    }

    fun binding() {
        binding.run {
            lifecycleOwner = this@SearchFragment
            searchView.isFocusable = false
            searchView.requestFocusFromTouch()
        }
    }

    private fun registerService() {
        suggestions = SearchRecentSuggestions(
            requireContext(),
            APPLICATION_ID,
            DATABASE_MODE_QUERIES
        )
        searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        requireActivity().intent.apply {
            if (Intent.ACTION_SEARCH == action) {
                getStringExtra(SearchManager.QUERY)?.also { query ->
                    SearchRecentSuggestions(
                        requireContext(),
                        APPLICATION_ID,
                        DATABASE_MODE_QUERIES
                    ).saveRecentQuery(query, null)
                }
            }
        }
    }

    private fun getQuerySearchView(position: Int): String {
        val selectedView = binding.searchView.suggestionsAdapter
        val cursor = selectedView.getItem(position) as Cursor
        val index = cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1)
        return cursor.getString(index).toString()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
