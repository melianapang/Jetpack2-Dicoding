package com.example.filmjetpacksub2.data.source.remote

import android.util.Log
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.data.source.remote.api.ApiClient
import com.example.filmjetpacksub2.data.source.remote.response.MovieDetailsResponse
import com.example.filmjetpacksub2.data.source.remote.response.MovieResponse
import com.example.filmjetpacksub2.data.source.remote.response.ShowsDetailsResponse
import com.example.filmjetpacksub2.data.source.remote.response.ShowsResponse
import com.example.filmjetpacksub2.utils.IdlingResource
import com.example.filmjetpacksub2.utils.MappingHelper
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
        private const val API_KEY = "af28284beab32ee6b8b90560faf0201f"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        IdlingResource.increment()
        ApiClient.api.getAllMoviesFromAPI(API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        IdlingResource.decrement()
                        val films = response.body()!!
                        callback.onAllMoviesReceived(MappingHelper.mapFilmMovies(films))
                    } else {
                        val error = response.errorBody()!!.toString()
                        val message = StringBuilder()
                        error.let {
                            try {
                                message.append(JSONObject(it).getString("message"))
                            } catch (e: JSONException) {
                            }
                            message.append("\n")
                        }
                        message.append("Error code : ${response.code()}")
                        Log.e(TAG, "onFailure: $message")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getAllShows(callback: LoadShowsCallback) {
        IdlingResource.increment()
        ApiClient.api.getAllShowsFromAPI(API_KEY)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onResponse(
                    call: Call<ShowsResponse>,
                    response: Response<ShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        IdlingResource.decrement()
                        val films = response.body()!!
                        callback.onAllShowsReceived(MappingHelper.mapFilmShows(films))
                    } else {
                        val error = response.errorBody()!!.toString()
                        val message = StringBuilder()
                        error.let {
                            try {
                                message.append(JSONObject(it).getString("message"))
                            } catch (e: JSONException) {
                            }
                            message.append("\n")
                        }
                        message.append("Error code : ${response.code()}")
                        Log.e(TAG, "onFailure: $message")
                    }
                }

                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getSelectedMovie(id: Int, callback: LoadSelectedMovieCallback) {
        IdlingResource.increment()
        ApiClient.api.getDetailMovieFromAPI(id, API_KEY)
            .enqueue(object : Callback<MovieDetailsResponse> {
                override fun onResponse(
                    call: Call<MovieDetailsResponse>,
                    response: Response<MovieDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        IdlingResource.decrement()
                        callback.onMovieReceived(MappingHelper.mapDetailFilmMovie(response.body()!!))
                    } else {
                        val error = response.errorBody()!!.toString()
                        val message = StringBuilder()
                        error.let {
                            try {
                                message.append(JSONObject(it).getString("message"))
                            } catch (e: JSONException) {
                            }
                            message.append("\n")
                        }
                        message.append("Error code : ${response.code()}")
                        Log.e(TAG, "onFailure: $message")
                    }
                }

                override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getSelectedShow(id: Int, callback: LoadSelectedShowCallback) {
        IdlingResource.increment()
        ApiClient.api.getDetailShowFromAPI(id, API_KEY)
            .enqueue(object : Callback<ShowsDetailsResponse> {
                override fun onResponse(
                    call: Call<ShowsDetailsResponse>,
                    response: Response<ShowsDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        IdlingResource.decrement()
                        callback.onShowReceived(MappingHelper.mapDetailFilmShow(response.body()!!))
                    } else {
                        val error = response.errorBody()!!.toString()
                        val message = StringBuilder()
                        error.let {
                            try {
                                message.append(JSONObject(it).getString("message"))
                            } catch (e: JSONException) {
                            }
                            message.append("\n")
                        }
                        message.append("Error code : ${response.code()}")
                        Log.e(TAG, "onFailure: $message")
                    }
                }

                override fun onFailure(call: Call<ShowsDetailsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: ArrayList<FilmEntity>)
    }

    interface LoadShowsCallback {
        fun onAllShowsReceived(tvShowsResponse: ArrayList<FilmEntity>)
    }

    interface LoadSelectedMovieCallback {
        fun onMovieReceived(detailMovieResponse: FilmEntity)
    }

    interface LoadSelectedShowCallback {
        fun onShowReceived(detailTvResponse: FilmEntity)
    }
}