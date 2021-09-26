package com.example.filmjetpacksub2.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmjetpacksub2.R
import com.example.filmjetpacksub2.data.source.FilmEntity
import com.example.filmjetpacksub2.databinding.HomeItemBinding
import com.example.filmjetpacksub2.ui.detail.DetailActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var listFilm: ArrayList<FilmEntity> = ArrayList()
    private var fragmentName: String = ""

    fun setData(list: ArrayList<FilmEntity>, fragment: Int) {
        if (listFilm.isNotEmpty()) listFilm.clear()
        listFilm.addAll(list)
        when (fragment) {
            0 -> fragmentName = "MOVIE"
            1 -> fragmentName = "TV SHOWS"
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val course = listFilm[position]
        val fragmentStr = fragmentName
        holder.bind(course, fragmentStr)
    }

    override fun getItemCount(): Int = listFilm.size

    class HomeViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmEntity, fragmentName: String) {
            with(binding) {
                tvJudul.text = film.judul
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val dateForm = dateFormat.format(film.tanggal)
                tvDate.text = dateForm

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FRAGMENT, fragmentName)
                    intent.putExtra(DetailActivity.EXTRA_NAME, film.judul)
                    intent.putExtra(DetailActivity.EXTRA_FILM_ID, film.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(film.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}