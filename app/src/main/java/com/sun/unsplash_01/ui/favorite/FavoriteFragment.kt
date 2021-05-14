package com.sun.unsplash_01.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.sun.unsplash_01.R
import com.sun.unsplash_01.databinding.FragmentFavoriteBinding
import com.sun.unsplash_01.ui.detail.PhotoDetailFragment
import com.sun.unsplash_01.ui.favorite.adapter.FavoriteAdapter
import com.sun.unsplash_01.utils.Status
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by inject()
    private val imageFavoriteAdapter by lazy {
        FavoriteAdapter {
            findNavController().navigate(
                R.id.imageDetailFragment,
                bundleOf(PhotoDetailFragment.BUNDLE_PHOTO_ID to it.id)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@FavoriteFragment.viewLifecycleOwner
            adapter = imageFavoriteAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() = with(favoriteViewModel) {
        getImages()
        favoriteImageLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    imageFavoriteAdapter.submitList(it.data)
                    imageFavoriteAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                }
            }
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
