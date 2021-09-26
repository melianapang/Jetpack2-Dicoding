package com.example.filmjetpacksub2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.FilmEntity

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private var movieId: Int = 0
    private var showId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(showId: Int) {
        this.showId = showId
    }

    fun getSelectedMovie(): LiveData<FilmEntity> = repository.getSelectedMovie(movieId)
    fun getSelectedTvShow(): LiveData<FilmEntity> = repository.getSelectedTvShow(showId)
}

