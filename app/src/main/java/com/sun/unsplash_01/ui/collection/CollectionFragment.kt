package com.sun.unsplash_01.ui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.unsplash_01.databinding.FragmentCollectionBinding
import com.sun.unsplash_01.ui.home.HomeFragment

class CollectionFragment : Fragment() {

    lateinit var binding: FragmentCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
