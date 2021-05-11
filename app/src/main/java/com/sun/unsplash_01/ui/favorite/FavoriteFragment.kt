package com.sun.unsplash_01.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.unsplash_01.R
import com.sun.unsplash_01.databinding.FragmentFavoriteBinding
import com.sun.unsplash_01.ui.home.HomeFragment

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
