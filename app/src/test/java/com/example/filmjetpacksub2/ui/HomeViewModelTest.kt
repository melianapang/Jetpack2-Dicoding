package com.example.filmjetpacksub2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.utils.DataDummy
import com.example.filmjetpacksub2.viewmodel.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<ArrayList<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun testGetFilm() {
        val dummyMovies = DataDummy.generateDummyMovie()
        val movies = MutableLiveData<ArrayList<FilmEntity>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies()).thenReturn(movies)
        val listFilm = viewModel.getFilm().value
        verify(repository).getAllMovies()
        assertNotNull(listFilm)
        assertEquals(6, listFilm?.size)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testGetTvShows() {
        val dummyShows = DataDummy.generateDummyTvShow()
        val shows = MutableLiveData<ArrayList<FilmEntity>>()
        shows.value = dummyShows

        `when`(repository.getAllShows()).thenReturn(shows)
        val listShows = viewModel.getTvShows().value
        verify(repository).getAllShows()
        assertNotNull(listShows)
        assertEquals(6, listShows?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }
}