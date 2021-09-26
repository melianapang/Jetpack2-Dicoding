package com.example.filmjetpacksub2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.data.source.remote.RemoteDataSource
import kotlin.collections.ArrayList

class FakeRepository(private val remoteDataSource: RemoteDataSource) : IDataSource {

    override fun getAllMovies(): LiveData<ArrayList<FilmEntity>> {
        val liveFilms = MutableLiveData<ArrayList<FilmEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse:  ArrayList<FilmEntity>) {
                liveFilms.postValue(moviesResponse)
            }
        })
        return liveFilms
    }

    override fun getAllShows(): LiveData<ArrayList<FilmEntity>> {
        val liveFilms = MutableLiveData<ArrayList<FilmEntity>>()
        remoteDataSource.getAllShows(object : RemoteDataSource.LoadShowsCallback {
            override fun onAllShowsReceived(tvShowsResponse: ArrayList<FilmEntity>) {
                liveFilms.postValue(tvShowsResponse)
            }
        })
        return liveFilms
    }

    override fun getSelectedMovie(idMovie: Int): LiveData<FilmEntity> {
        val liveData = MutableLiveData<FilmEntity>()
        remoteDataSource.getSelectedMovie(idMovie, object :RemoteDataSource.LoadSelectedMovieCallback {
            override fun onMovieReceived(detailMovieResponse: FilmEntity) {
                liveData.postValue(detailMovieResponse)
            }
        })
        return liveData
    }

    override fun getSelectedTvShow(idShow: Int): LiveData<FilmEntity> {
        val liveData = MutableLiveData<FilmEntity>()
        remoteDataSource.getSelectedShow(idShow, object : RemoteDataSource.LoadSelectedShowCallback {
            override fun onShowReceived(detailTvResponse: FilmEntity) {
                liveData.postValue(detailTvResponse)
            }
        })
        return liveData
    }
}