package com.android.amm.androidcodemanagementtest.view.ui.movielist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.amm.androidcodemanagementtest.constant.Constants
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.row_popular_movie_item.view.*
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.*
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.iv_movie_path
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.movie_layout
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.tb_favourite
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.tv_percent
import kotlinx.android.synthetic.main.row_upcoming_movie_item.view.tv_title

/**
 * Created by ayemyatmon on 17,April,2022
 */
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