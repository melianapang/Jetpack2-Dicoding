package com.example.filmjetpacksub2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.filmjetpacksub2.ui.home.MainActivity
import com.example.filmjetpacksub2.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummySize = 20

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(IdlingResource.getIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.getIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummySize
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_home)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummySize
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_judul_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tahun_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.img_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_home)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_judul_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tahun_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.img_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
    }

    @Test
    fun shareMovie(){
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        1,
                        click()
                )
        )
        onView(withId(R.id.btn_share)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share)).perform(click())
    }

    @Test
    fun shareTvShow(){
        onView(withId(R.id.rv_home)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_home)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        1,
                        click()
                )
        )
        onView(withId(R.id.btn_share)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share)).perform(click())
    }
}