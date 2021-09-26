package com.example.filmjetpacksub2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmjetpacksub2.R
import com.example.filmjetpacksub2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.movies_tab,
            R.string.tvshows_tab
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managePagerAdapter()
    }

    private fun managePagerAdapter() {
        val sectionsPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}