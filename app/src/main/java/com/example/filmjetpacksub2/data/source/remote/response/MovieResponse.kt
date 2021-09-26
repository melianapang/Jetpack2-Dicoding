package com.example.filmjetpacksub2.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(
        @SerializedName("results")
        val results: ArrayList<MovieObject> = ArrayList()
)

@Parcelize
data class MovieObject(
        @SerializedName("id")
        val id: Int,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("original_title")
        val title: String,
        @SerializedName("poster_path")
        val posterPath: String,
) : Parcelable

data class MovieDetailsResponse(
        val id: Int = 0,
        @SerializedName("release_date")
        val releaseDate: String = "",
        @SerializedName("original_title")
        val title: String = "",
        @SerializedName("runtime")
        val runTime: Int = 0,
        val overview: String = "",
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("genres")
        val genres: ArrayList<Genres> = ArrayList()
)
