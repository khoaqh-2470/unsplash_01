package com.sun.unsplash_01.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.unsplash_01.R
import com.sun.unsplash_01.databinding.FragmentDetailBinding
import com.sun.unsplash_01.extensions.hideStatusBar

class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().hideStatusBar()
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        ).apply {
            lifecycleOwner = this@ImageDetailFragment
        }
        return binding.root
    }

    companion object {
        fun newInstance() = ImageDetailFragment()
    }
}
