package com.example.filmjetpacksub2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.utils.DataDummy
import com.example.filmjetpacksub2.viewmodel.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.text.DateFormat
import java.text.SimpleDateFormat

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTestCase {
    private lateinit var viewModel: DetailViewModel
    private val dummyShowId = DataDummy.generateDummyTvShow()[0].id
    private val dummyMovieId = DataDummy.generateDummyMovie()[0].id

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test(expected = Exception::class)
    @Throws(NullPointerException::class)
    fun testEmptyDataMovie() {
        val dummyMovie = DataDummy.generateDummyMovie()[0]
        val movie = MutableLiveData<FilmEntity>()
        movie.value = dummyMovie

        `when`(repository.getSelectedMovie(dummyMovieId)).thenReturn(movie)
        viewModel.setSelectedMovie(dummyMovieId)
        val filmSelected = viewModel.getSelectedMovie().value
        thrown.expect(NullPointerException::class.java)
        assertEquals("The data is empty", "", filmSelected?.judul)

        viewModel.getSelectedMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test(expected = Exception::class)
    @Throws(NullPointerException::class)
    fun testEmptyDataTvShows() {
        val dummyShow = DataDummy.generateDummyMovie()[0]
        val show = MutableLiveData<FilmEntity>()
        show.value = dummyShow

        `when`(repository.getSelectedMovie(dummyMovieId)).thenReturn(show)
        viewModel.setSelectedTvShow(dummyShowId)
        val showSelected = viewModel.getSelectedTvShow().value
        thrown.expect(NullPointerException::class.java)
        assertEquals("The data is empty", "", showSelected?.judul)

        viewModel.getSelectedTvShow().observeForever(observer)
        verify(observer).onChanged(dummyShow)
    }

    @Test(expected = Exception::class)
    fun testFalseFullDateFormat() {
        viewModel.setSelectedTvShow(dummyShowId)
        val dummyData = viewModel.getSelectedTvShow().value
        val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.format(dummyData?.tanggal)
        assertNotSame("The data are the same", dummyData?.tanggal, date)
    }

    @Test(expected = Exception::class)
    fun nullAndThrowsShows() {
        val detailViewModel = mock(DetailViewModel::class.java)
        doThrow().`when`(detailViewModel).setSelectedMovie(dummyShowId)
        detailViewModel.setSelectedMovie(dummyShowId)
    }

    @Test(expected = Exception::class)
    fun nullAndThrowsMovies() {
        val detailViewModel = mock(DetailViewModel::class.java)
        doThrow().`when`(detailViewModel).setSelectedTvShow(dummyMovieId)
        detailViewModel.setSelectedTvShow(dummyMovieId)
    }
}