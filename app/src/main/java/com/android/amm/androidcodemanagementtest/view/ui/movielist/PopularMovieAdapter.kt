package com.android.amm.androidcodemanagementtest.view.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.amm.androidcodemanagementtest.R
import com.android.amm.androidcodemanagementtest.models.MovieModel

/**
 * Created by ayemyatmon on 17,April,2022
 */
class PopularMovieAdapter (private val movieList: List<MovieModel>
): RecyclerView.Adapter<PopularMovieViewHolder>() {

    private lateinit var onMovieClickListener: (movie: MovieModel) -> Unit
    private lateinit var onFavouriteClickListener: (movie: MovieModel) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMovieViewHolder {

        return PopularMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_popular_movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {

        holder.bind(movieList[position], onMovieClickListener, onFavouriteClickListener)

    }

    override fun getItemCount(): Int {

        return movieList.size
    }

    fun addOnMovieClickListener(listener: (movie: MovieModel) -> Unit) {

        onMovieClickListener = listener

    }

    fun addOnFavouriteClickListener(listener: (movie: MovieModel) -> Unit) {

        onFavouriteClickListener = listener

    }

}