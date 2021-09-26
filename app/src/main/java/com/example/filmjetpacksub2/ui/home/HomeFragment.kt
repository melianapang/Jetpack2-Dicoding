package com.example.filmjetpacksub2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmjetpacksub2.databinding.FragmentHomeBinding
import com.example.filmjetpacksub2.viewmodel.HomeViewModel
import com.example.filmjetpacksub2.viewmodel.ViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private var pos: Int = 0
    private var rvAdapter = HomeAdapter()

    companion object {
        const val POSITION = "position"

        @JvmStatic
        fun newInstance(position: Int) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        showLoading(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        homeViewModel = ViewModelProvider(
            this, factory
        ).get(HomeViewModel::class.java)

        pos = arguments?.getInt(POSITION, 0) ?: 0
        initialiseRecyclerView()
    }

    private fun initialiseRecyclerView() {
        with(binding.rvHome) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = rvAdapter
        }
        generateData()
    }

    private fun generateData() {
        when (pos) {
            0 -> {
                homeViewModel.getFilm().observe(this, { listFilm ->
                    rvAdapter.setData(listFilm, pos)
                    rvAdapter.notifyDataSetChanged()
                    showLoading(false)
                })
            }
            1 -> {
                homeViewModel.getTvShows().observe(this, { listShows ->
                    rvAdapter.setData(listShows, pos)
                    rvAdapter.notifyDataSetChanged()
                    showLoading(false)
                })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }
}