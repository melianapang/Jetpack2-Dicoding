package com.example.filmjetpacksub2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.utils.DataDummy
import com.example.filmjetpacksub2.viewmodel.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyShowId = DataDummy.generateDummyTvShow()[0].id
    private val dummyMovieId = DataDummy.generateDummyMovie()[0].id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository :Repository

    @Mock
    private lateinit var observer: Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
        viewModel.setSelectedMovie(dummyMovieId)
        viewModel.setSelectedTvShow(dummyShowId)
    }

    @Test
    fun testGetSelectedMovie() {
        val dummyMovie = DataDummy.generateDummyMovie()[0]
        val movie = MutableLiveData<FilmEntity>()
        movie.value = dummyMovie

        `when`(repository.getSelectedMovie(dummyMovieId)).thenReturn(movie)
        val selectedMovie = viewModel.getSelectedMovie().value
        assertNotNull(selectedMovie)
        assertEquals(dummyMovie.posterPath, selectedMovie?.posterPath)
        assertEquals(dummyMovie.judul, selectedMovie?.judul)
        assertEquals(dummyMovie.desc, selectedMovie?.desc)
        assertEquals(dummyMovie.tanggal, selectedMovie?.tanggal)
        assertEquals(dummyMovie.genre, selectedMovie?.genre)
        assertEquals(dummyMovie.duration, selectedMovie?.duration)

        viewModel.getSelectedMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testGetSelectedTvShow() {
        val dummyShow = DataDummy.generateDummyMovie()[0]
        val show = MutableLiveData<FilmEntity>()
        show.value = dummyShow

        `when`(repository.getSelectedTvShow(dummyShowId)).thenReturn(show)
        val selectedShow = viewModel.getSelectedTvShow().value
        assertNotNull(selectedShow)
        assertEquals(dummyShow.posterPath, selectedShow?.posterPath)
        assertEquals(dummyShow.judul, selectedShow?.judul)
        assertEquals(dummyShow.desc, selectedShow?.desc)
        assertEquals(dummyShow.tanggal, selectedShow?.tanggal)
        assertEquals(dummyShow.genre, selectedShow?.genre)
        assertEquals(dummyShow.duration, selectedShow?.duration)

        viewModel.getSelectedTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyShow)
    }
}