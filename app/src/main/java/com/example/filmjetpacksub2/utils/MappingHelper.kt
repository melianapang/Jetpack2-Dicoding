package com.example.filmjetpacksub2.utils

import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.data.source.remote.response.MovieDetailsResponse
import com.example.filmjetpacksub2.data.source.remote.response.MovieResponse
import com.example.filmjetpacksub2.data.source.remote.response.ShowsDetailsResponse
import com.example.filmjetpacksub2.data.source.remote.response.ShowsResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object MappingHelper {
    private const val POSTER_PATH_LINK = "https://image.tmdb.org/t/p/w500"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun mapFilmMovies(films: MovieResponse): ArrayList<FilmEntity> {
        val arrFilms = ArrayList<FilmEntity>()
        for (data in films.results) {
            val pathStr = StringBuilder()
            pathStr.append(POSTER_PATH_LINK).append(data.posterPath)

            val film = FilmEntity(
                id = data.id,
                judul = data.title,
                tanggal = dateFormat.parse(data.releaseDate),
                posterPath = pathStr.toString(),
            )
            arrFilms.add(film)
        }
        return arrFilms
    }

    fun mapFilmShows(films: ShowsResponse): ArrayList<FilmEntity> {
        val arrFilms = ArrayList<FilmEntity>()
        for (data in films.results) {
            val pathStr = StringBuilder()
            pathStr.append(POSTER_PATH_LINK).append(data.posterPath)

            val film = FilmEntity(
                id = data.id,
                judul = data.title,
                tanggal = dateFormat.parse(data.firstAirDate),
                posterPath = pathStr.toString(),
            )
            arrFilms.add(film)
        }
        return arrFilms

    }

    fun mapDetailFilmMovie(data: MovieDetailsResponse): FilmEntity {
        val genreStr = StringBuilder()
        val genreSize = data.genres.size
        for (i in 0 until genreSize - 1) {
            val genre = data.genres[i].name
            genreStr.append(genre)
            if (i < genreSize - 2) genreStr.append(", ")
        }

        val pathStr = StringBuilder()
        pathStr.append(POSTER_PATH_LINK).append(data.posterPath)

        return FilmEntity(
            id = data.id,
            judul = data.title,
            desc = data.overview,
            duration = data.runTime,
            tanggal = dateFormat.parse(data.releaseDate),
            posterPath = pathStr.toString(),
            genre = genreStr.toString()
        )
    }

    fun mapDetailFilmShow(data: ShowsDetailsResponse): FilmEntity {
        val genreStr = StringBuilder()
        val genreSize = data.genres.size
        for (i in 0 until genreSize - 1) {
            val genre = data.genres[i].name
            genreStr.append(genre)
            if (i < genreSize - 2) genreStr.append(", ")
        }

        val pathStr = StringBuilder()
        pathStr.append(POSTER_PATH_LINK).append(data.posterPath)

        return FilmEntity(
            id = data.id,
            judul = data.title,
            desc = data.overview,
            duration = data.runTime[0],
            tanggal = dateFormat.parse(data.firstAirDate),
            posterPath = pathStr.toString(),
            genre = genreStr.toString(),
            episodes = data.numOfEps,
            seasons = data.numOfSeasons
        )

    }
}