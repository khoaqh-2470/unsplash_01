package com.sun.unsplash_01.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sun.unsplash_01.R
import com.sun.unsplash_01.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val images = listOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSlide()
    }

    private fun initSlide() {
        val homeAdapter = HomeSlideAdapter(requireActivity(), images) {
            findNavController().navigate(R.id.imageDetailFragment)
        }
        binding.viewPagerSlide.apply {
            adapter = homeAdapter
            offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
            pageMargin = ITEM_DISTANCE
        }
        var currentPage = 0
        val handler = Looper.myLooper()?.let { Handler(it) }
        val runnable = Runnable {
            if (currentPage > images.size) {
                currentPage = 0
            }
            binding.viewPagerSlide.setCurrentItem(currentPage++, true)
        }
        Timer().run {
            schedule(object : TimerTask() {
                override fun run() {
                    handler?.post(runnable)
                }
            }, DELAY_TIME, DELAY_NEXT_ITEM)
        }
    }

    companion object {

        private const val DELAY_TIME = 2000L
        private const val DELAY_NEXT_ITEM = 3000L
        private const val ITEM_DISTANCE = 30
        private const val OFF_SCREEN_PAGE_LIMIT = 5

        fun newInstance() = HomeFragment()
    }
}
