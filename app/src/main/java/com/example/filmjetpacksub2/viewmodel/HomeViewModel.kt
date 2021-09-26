package com.example.filmjetpacksub2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.FilmEntity

class HomeViewModel(private val repository: Repository) : ViewModel() {
    fun getFilm(): LiveData<ArrayList<FilmEntity>> = repository.getAllMovies()
    fun getTvShows(): LiveData<ArrayList<FilmEntity>> = repository.getAllShows()
}