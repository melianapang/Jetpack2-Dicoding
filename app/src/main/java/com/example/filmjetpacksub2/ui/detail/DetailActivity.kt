package com.example.filmjetpacksub2.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmjetpacksub2.R
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.databinding.ActivityDetailBinding
import com.example.filmjetpacksub2.viewmodel.DetailViewModel
import com.example.filmjetpacksub2.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detViewModel: DetailViewModel

    companion object {
        const val EXTRA_NAME = "extra_name_film"
        const val EXTRA_FRAGMENT = "extra_fragment"
        const val EXTRA_FILM_ID = "extra_film_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        val factory = ViewModelFactory.getInstance()
        detViewModel = ViewModelProvider(
            this, factory
        ).get(DetailViewModel::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            val filmId = bundle.getInt(EXTRA_FILM_ID)
            val filmStr = bundle.getString(EXTRA_NAME)
            println(filmId)
            when (bundle.getString(EXTRA_FRAGMENT)) {
                "MOVIE" -> {
                    if (filmStr != null) {
                        detViewModel.setSelectedMovie(filmId)
                        detViewModel.getSelectedMovie().observe(this, { selectedMovie ->
                            setSupportBar(filmStr)
                            manageData(selectedMovie)
                            showLoading(false)
                        })
                    }
                }
                "TV SHOWS" -> {
                    if (filmStr != null) {
                        detViewModel.setSelectedTvShow(filmId)
                        detViewModel.getSelectedTvShow().observe(this, { selectedShow ->
                            setSupportBar(filmStr)
                            manageData(selectedShow)
                            showLoading(false)
                        })
                    }
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setSupportBar(movieName: String) {
        supportActionBar?.apply {
            customView = setTitleBar(movieName)
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setTitleBar(movieName: String): TextView {
        return TextView(this).apply {
            text = movieName

            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            layoutParams = params
            params.gravity = Gravity.START

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextAppearance(
                    android.R.style.TextAppearance_Material_Widget_ActionBar_Title
                )
            } else {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                setTypeface(null, Typeface.BOLD)
            }
            setTextColor(Color.WHITE)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun manageData(film: FilmEntity) {
        with(binding) {
            tvJudulDetail.text = film.judul
            tvOverviewContent.text = film.desc
            tvDuration.text = resources.getQuantityString(
                R.plurals.durations,
                film.duration,
                film.duration
            )
            tvGenre.text =
                if (film.genre.isBlank()) resources.getString(R.string.def_genre) else film.genre

            if (film.episodes > 0) {
                tvEpisodes.visibility = View.VISIBLE
                tvEpisodes.text = resources.getQuantityString(
                    R.plurals.numOfEps,
                    film.episodes,
                    film.episodes
                )
            }

            if (film.seasons > 0) {
                tvSeasons.visibility = View.VISIBLE
                tvSeasons.text = resources.getQuantityString(
                    R.plurals.numOfSeasons,
                    film.seasons,
                    film.seasons
                )
            }

            val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            val yearForm = yearFormat.format(film.tanggal)
            val dateForm = dateFormat.format(film.tanggal)
            tvTahunDetail.text = yearForm
            tvDateDetail.text = dateForm

            btnShare.setOnClickListener { onShareClick(film) }

            Glide.with(root)
                .load(film.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPosterDetail)
        }
    }

    private fun onShareClick(film: FilmEntity) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this@DetailActivity)
            .setType(mimeType)
            .setChooserTitle(resources.getString(R.string.share_title))
            .setText(resources.getString(R.string.share_text, film.judul))
            .startChooser()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }
}