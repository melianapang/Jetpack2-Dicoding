package com.example.filmjetpacksub2.data

import androidx.lifecycle.LiveData
import com.example.filmjetpacksub2.data.source.FilmEntity

interface IDataSource {
    fun getAllMovies(): LiveData<ArrayList<FilmEntity>>
    fun getAllShows(): LiveData<ArrayList<FilmEntity>>

    fun getSelectedTvShow(idMovie: Int): LiveData<FilmEntity>
    fun getSelectedMovie(idShow: Int): LiveData<FilmEntity>
}