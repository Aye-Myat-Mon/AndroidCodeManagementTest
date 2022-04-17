package com.android.amm.androidcodemanagementtest.view.ui

import android.os.Bundle
import com.android.amm.androidcodemanagementtest.R
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.android.amm.androidcodemanagementtest.view.ui.moviedetail.MovieDetailFragment
import com.android.amm.androidcodemanagementtest.view.ui.movielist.MovieListFragment
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openMovieListFragment()
        }
    }

    fun openMovieListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frag_container_main,
                MovieListFragment(),
                MovieListFragment.tag_movie_list
            )
            .commit()
    }

    fun openMovieDetailFragment(movie: MovieModel) {
        val movieDetailFragment = MovieDetailFragment()

        Bundle().apply {
            putString(MovieDetailFragment.key_movie_data, Gson().toJson(movie))
            movieDetailFragment.arguments = this

        }
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frag_container_main,
                movieDetailFragment,
                MovieDetailFragment.tag_movie_detail
            )
            .commit()
    }
}