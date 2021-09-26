package com.example.filmjetpacksub2.utils

import android.annotation.SuppressLint
import com.example.filmjetpacksub2.R
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.data.source.remote.response.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object DataDummy {
    @SuppressLint("SimpleDateFormat")
    private val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    @SuppressLint("SimpleDateFormat")
    private val dateFormatYear: DateFormat = SimpleDateFormat("yyyy", Locale.getDefault())

    fun generateDummyMovie(): ArrayList<FilmEntity> {
        val films = ArrayList<FilmEntity>()

        films.add(
                FilmEntity(
                        1,
                        "Alita: Battle Angel",
                        dateFormat.parse("02/14/2019"),
                        R.drawable.poster_alita.toString(),
                        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                        "Action, Science Fiction, Adventure",
                        50
                )
        )
        films.add(
                FilmEntity(
                        2,
                        "Bohemian Rhapsody",
                        dateFormat.parse("11/2/2018"),
                        R.drawable.poster_bohemian.toString(),
                        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                        "Music, Drama, History",
                        120
                )
        )
        films.add(
                FilmEntity(
                        1,
                        "Alita: Battle Angel",
                        dateFormat.parse("02/14/2019"),
                        R.drawable.poster_alita.toString(),
                        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                        "Action, Science Fiction, Adventure",
                        50
                )
        )
        films.add(
                FilmEntity(
                        2,
                        "Bohemian Rhapsody",
                        dateFormat.parse("11/2/2018"),
                        R.drawable.poster_bohemian.toString(),
                        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                        "Music, Drama, History",
                        120
                )
        )
        films.add(
                FilmEntity(
                        1,
                        "Alita: Battle Angel",
                        dateFormat.parse("02/14/2019"),
                        R.drawable.poster_alita.toString(),
                        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                        "Action, Science Fiction, Adventure",
                        50
                )
        )
        films.add(
                FilmEntity(
                        2,
                        "Bohemian Rhapsody",
                        dateFormat.parse("11/2/2018"),
                        R.drawable.poster_bohemian.toString(),
                        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                        "Music, Drama, History",
                        120
                )
        )

        return films
    }

    fun generateDummyTvShow(): ArrayList<FilmEntity> {

        val shows = ArrayList<FilmEntity>()

        shows.add(
                FilmEntity(
                        1,
                        "Arrow",
                        dateFormatYear.parse("2012"),
                        R.drawable.poster_arrow.toString(),
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                        "Crime, Drama, Mystery, Action & Adventure",
                        42,
                        19,
                        6
                )
        )
        shows.add(
                FilmEntity(
                        2,
                        "Fairy Tail the Movie: Phoenix Priestess",
                        dateFormat.parse("8/18/2012"),
                        R.drawable.poster_fairytail.toString(),
                        "Mighty mages must fight to save the world when, in his quest for immortality, a mad prince foolishly unleashes an ancient and powerful force.",
                        "Action, Comedy, Drama, Adventure, Animation, Fantasy",
                        126,
                        8,
                        1
                )
        )
        shows.add(
                FilmEntity(
                        1,
                        "Arrow",
                        dateFormatYear.parse("2012"),
                        R.drawable.poster_arrow.toString(),
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                        "Crime, Drama, Mystery, Action & Adventure",
                        42,
                        19,
                        6
                )
        )
        shows.add(
                FilmEntity(
                        2,
                        "Fairy Tail the Movie: Phoenix Priestess",
                        dateFormat.parse("8/18/2012"),
                        R.drawable.poster_fairytail.toString(),
                        "Mighty mages must fight to save the world when, in his quest for immortality, a mad prince foolishly unleashes an ancient and powerful force.",
                        "Action, Comedy, Drama, Adventure, Animation, Fantasy",
                        126,
                        8,
                        1
                )
        )
        shows.add(
                FilmEntity(
                        1,
                        "Arrow",
                        dateFormatYear.parse("2012"),
                        R.drawable.poster_arrow.toString(),
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                        "Crime, Drama, Mystery, Action & Adventure",
                        42,
                        19,
                        6
                )
        )
        shows.add(
                FilmEntity(
                        2,
                        "Fairy Tail the Movie: Phoenix Priestess",
                        dateFormat.parse("8/18/2012"),
                        R.drawable.poster_fairytail.toString(),
                        "Mighty mages must fight to save the world when, in his quest for immortality, a mad prince foolishly unleashes an ancient and powerful force.",
                        "Action, Comedy, Drama, Adventure, Animation, Fantasy",
                        126,
                        8,
                        1
                )
        )
        return shows
    }
}