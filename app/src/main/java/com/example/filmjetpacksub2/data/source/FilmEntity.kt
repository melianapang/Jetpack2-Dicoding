package com.example.filmjetpacksub2.data.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FilmEntity(
    var id: Int = 0,
    var judul: String = "",
    var tanggal: Date = Date(2018),
    var posterPath: String = "",
    var desc: String = "",
    var genre: String = "",
    var duration: Int = 0,
    var episodes: Int = 0,
    var seasons: Int = 0
) : Parcelable