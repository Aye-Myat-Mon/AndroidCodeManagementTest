package com.android.amm.androidcodemanagementtest.view.ui.movielist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.amm.androidcodemanagementtest.constant.Constants
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.*


class UpcomingMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(movie: MovieModel, listener: (movie: MovieModel) -> Unit, favouriteListener: (movie: MovieModel) -> Unit) {
        itemView.tv_title.text = movie.title
        itemView.tv_percent.text = movie.vote_average
        itemView.tv_overview.text = movie.overview
        itemView.tb_favourite.isChecked = movie.isFavourite

        Glide.with(itemView.context)
            .load(Constants.POSTER_BASE_URL + movie.poster_path)
            .into(itemView.iv_movie_path)

        itemView.movie_layout.setOnClickListener {

            listener.invoke(movie)

        }

        itemView.tb_favourite.setOnCheckedChangeListener { _, isChecked ->
            favouriteListener.invoke(movie.apply { isFavourite = isChecked })
        }
    }
}