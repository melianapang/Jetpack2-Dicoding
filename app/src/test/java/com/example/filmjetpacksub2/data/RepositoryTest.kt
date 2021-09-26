package com.example.filmjetpacksub2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmjetpacksub2.data.source.remote.RemoteDataSource
import com.example.filmjetpacksub2.utils.DataDummy
import com.example.filmjetpacksub2.utils.LiveDataTestUtils
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)
    private val dummyId = 1

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testGetFilm() {
        val moviesResponse = DataDummy.generateDummyMovie()
        doAnswer{ invocaton ->
            (invocaton.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val movies = LiveDataTestUtils.getValue(repository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movies)
        assertEquals(moviesResponse.size.toLong(), movies.size.toLong())
    }

    @Test
    fun testGetTvShows() {
        val showsResponse = DataDummy.generateDummyTvShow()
        doAnswer{ invocaton ->
            (invocaton.arguments[0] as RemoteDataSource.LoadShowsCallback)
                    .onAllShowsReceived(showsResponse)
            null
        }.`when`(remote).getAllShows(any())
        val shows = LiveDataTestUtils.getValue(repository.getAllShows())
        verify(remote).getAllShows(any())
        assertNotNull(shows)
        assertEquals(showsResponse.size.toLong(), shows.size.toLong())
    }

    @Test
    fun testGetSelectedMovie() {
        val movieObject = DataDummy.generateDummyMovie()[0]
        doAnswer{ invocaton ->
            (invocaton.arguments[1] as RemoteDataSource.LoadSelectedMovieCallback)
                    .onMovieReceived(movieObject)
            null
        }.`when`(remote).getSelectedMovie(eq(dummyId),any())
        val movie = LiveDataTestUtils.getValue(repository.getSelectedMovie(dummyId))
        verify(remote).getSelectedMovie(eq(dummyId),any())
        assertNotNull(movie)
        assertEquals(movieObject.id, movie.id)
        assertEquals(movieObject.judul, movie.judul)
        assertEquals(movieObject.duration, movie.duration)
        assertEquals(movieObject.desc, movie.desc)
        assertEquals(movieObject.genre, movie.genre)
        assertEquals(movieObject.tanggal, movie.tanggal)
    }

    @Test
    fun testGetSelectedTvShow() {
        val showObject = DataDummy.generateDummyTvShow()[0]
        doAnswer{ invocaton ->
            (invocaton.arguments[1] as RemoteDataSource.LoadSelectedShowCallback)
                    .onShowReceived(showObject)
            null
        }.`when`(remote).getSelectedShow(eq(dummyId),any())
        val show = LiveDataTestUtils.getValue(repository.getSelectedTvShow(dummyId))
        verify(remote).getSelectedShow(eq(dummyId),any())
        assertNotNull(show)
        assertEquals(showObject.id, show.id)
        assertEquals(showObject.judul, show.judul)
        assertEquals(showObject.duration, show.duration)
        assertEquals(showObject.desc, show.desc)
        assertEquals(showObject.genre, show.genre)
        assertEquals(showObject.tanggal, show.tanggal)
        assertEquals(showObject.episodes, show.episodes)
        assertEquals(showObject.seasons, show.seasons)
    }
}